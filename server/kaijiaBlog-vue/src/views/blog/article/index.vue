<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="文章标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入文章标题"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作者" prop="authorNickName">
        <el-input
          v-model="queryParams.authorNickName"
          placeholder="请输入作者"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类" prop="categoryName">
        <el-input
          v-model="queryParams.categoryName"
          placeholder="请输入分类"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="浏览次数" prop="viewCount">
        <el-input
          v-model="queryParams.viewCount"
          placeholder="请输入浏览次数"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable
          v-model="queryParams.createTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新时间" prop="updateTime">
        <el-date-picker clearable
          v-model="queryParams.updateTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择更新时间">
        </el-date-picker>
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
          v-hasPermi="['blog:article:add']"
        >发布</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['blog:article:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['blog:article:remove']"
        >删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['blog:article:export']"
        >导出</el-button>
      </el-col> -->
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="articleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键" align="center" prop="id" /> -->
      <el-table-column label="文章标题" align="center" prop="title" />
      <!-- <el-table-column label="文章内容" align="center" prop="content" /> -->
      <el-table-column label="作者" align="center" prop="authorNickName" />
      <el-table-column label="分类" align="center" prop="categoryName" />
      <el-table-column label="文章状态" align="center" prop="status" />
      <el-table-column label="浏览次数" align="center" prop="viewCount" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['blog:article:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['blog:article:remove']">删除</el-button>
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

    <!-- 添加或修改文章管理对话框 -->
    <el-dialog :title="title" v-model="open" width="1700px" append-to-body>
      <el-form ref="articleRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="文章标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入文章标题" />
        </el-form-item>
        <!-- <el-form-item label="文章内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item> -->
        <el-form-item>
          <MdEditor v-model="form.content" />
        </el-form-item>
        <el-form-item label="作者ID" prop="authorId">
          <el-input v-model="form.authorId" placeholder="请输入作者ID" />
        </el-form-item>
        <el-form-item label="分类ID" prop="categoryId">
          <el-input v-model="form.categoryId" placeholder="请输入分类ID" />
        </el-form-item>
        <el-form-item label="浏览次数" prop="viewCount">
          <el-input v-model="form.viewCount" placeholder="请输入浏览次数" />
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

<script setup name="Article">
import { listArticle, getArticle, delArticle, addArticle, updateArticle } from "@/api/blog/article";
import { MdEditor } from "md-editor-v3";
import 'md-editor-v3/lib/style.css'

const { proxy } = getCurrentInstance();
const articleList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

// onMounted(() => {
//   //获取用户字典
//   listUser().then(res => {
//     res.rows.map(user => {
//       userDict.value.push({id: user.userId,nickName: user.nickName})
//     })
//   })
//   //获取文章分类字典
//   listCategory().then(res => {
//     res.rows.map(category => {
//       categoryDict.value.push({id: category.id,categoryName: category.name})
//     })
//   })
// })
// const authorFormatter = (row) => {
//   userDict.value.map(user => {
//     if (user.id === row.authorId) {
//       console.log(user.nickName)
//       return user.nickName;
//     }else {
//       return row;
//     }
//   })
// }

// function categoryFormatter(row) {

// }
const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    title: null,
    content: null,
    authorId: null,
    authorNickName: null,
    categoryId: null,
    categoryName: null,
    status: null,
    viewCount: null,
    createTime: null,
    updateTime: null
  },
  rules: {
    title: [
      { required: true, message: "文章标题不能为空", trigger: "blur" }
    ],
    content: [
      { required: true, message: "文章内容不能为空", trigger: "blur" }
    ],
    authorId: [
      { required: true, message: "作者ID不能为空", trigger: "blur" }
    ],
    createTime: [
      { required: true, message: "创建时间不能为空", trigger: "blur" }
    ],
    updateTime: [
      { required: true, message: "更新时间不能为空", trigger: "blur" }
    ]
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询文章管理列表 */
function getList() {
  loading.value = true;
  listArticle(queryParams.value).then(response => {
    articleList.value = response.rows;
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
    title: null,
    content: '',
    authorId: null,
    authorNickName: null,
    categoryId: null,
    categoryName: null,
    status: null,
    viewCount: null,
    createTime: null,
    updateTime: null
  };
  proxy.resetForm("articleRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
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
  title.value = "添加文章管理";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getArticle(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改文章管理";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["articleRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateArticle(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addArticle(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除文章管理编号为"' + _ids + '"的数据项？').then(function() {
    return delArticle(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('blog/article/export', {
    ...queryParams.value
  }, `article_${new Date().getTime()}.xlsx`)
}

getList();
</script>