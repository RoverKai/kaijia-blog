<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="文章标题" prop="articleTitle">
        <el-input
          v-model="queryParams.articleTitle"
          placeholder="请输入文章标题"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户名" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户名"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="访问者IP地址" prop="ipAddress">
        <el-input
          v-model="queryParams.ipAddress"
          placeholder="请输入访问者IP地址"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="访问时间" style="width: 308px">
        <el-date-picker
          v-model="daterangeVisitedAt"
          value-format="YYYY-MM-DD"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['blog:log:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['blog:log:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['blog:log:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['blog:log:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="logList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="文章ID" align="center" prop="articleId" />
      <el-table-column label="文章标题" align="center" prop="articleTitle" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="用户名" align="center" prop="userName" />
      <el-table-column label="访问者IP地址" align="center" prop="ipAddress" />
      <el-table-column label="访问时间" align="center" prop="visitedAt" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.visitedAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['blog:log:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['blog:log:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改浏览记录对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="logRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="文章ID" prop="articleId">
          <el-input v-model="form.articleId" placeholder="请输入文章ID" />
        </el-form-item>
        <el-form-item label="用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="访问者IP地址" prop="ipAddress">
          <el-input v-model="form.ipAddress" placeholder="请输入访问者IP地址" />
        </el-form-item>
        <el-form-item label="访问时间" prop="visitedAt">
          <el-date-picker clearable
            v-model="form.visitedAt"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择访问时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Log">
import { listLog, getLog, delLog, addLog, updateLog } from "@/api/blog/log";

const { proxy } = getCurrentInstance();

const logList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const daterangeVisitedAt = ref([]);

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    articleId: null,
    userId: null,
    ipAddress: null,
    visitedAt: null
  },
  rules: {
    ipAddress: [
      { required: true, message: "访问者IP地址不能为空", trigger: "blur" }
    ],
    visitedAt: [
      { required: true, message: "访问时间不能为空", trigger: "blur" }
    ]
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询浏览记录列表 */
function getList() {
  loading.value = true;
  queryParams.value.params = {};
  if (null != daterangeVisitedAt && '' != daterangeVisitedAt) {
    queryParams.value.params["beginVisitedAt"] = daterangeVisitedAt.value[0];
    queryParams.value.params["endVisitedAt"] = daterangeVisitedAt.value[1];
  }
  listLog(queryParams.value).then(response => {
    logList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    articleId: null,
    userId: null,
    ipAddress: null,
    visitedAt: null
  };
  proxy.resetForm("logRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  daterangeVisitedAt.value = [];
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加浏览记录";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getLog(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改浏览记录";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["logRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateLog(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addLog(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除浏览记录编号为"' + _ids + '"的数据项？').then(function() {
    return delLog(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('blog/log/export', {
    ...queryParams.value
  }, `log_${new Date().getTime()}.xlsx`)
}

getList();
</script>