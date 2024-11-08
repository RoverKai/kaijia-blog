import request from '@/utils/request'

// 查询文章分类管理列表
export function listCategory(query) {
  return request({
    url: '/blog/category/list',
    method: 'get',
    params: query
  })
}

// 查询文章分类管理详细
export function getCategory(id) {
  return request({
    url: '/blog/category/' + id,
    method: 'get'
  })
}

// 新增文章分类管理
export function addCategory(data) {
  return request({
    url: '/blog/category',
    method: 'post',
    data: data
  })
}

// 修改文章分类管理
export function updateCategory(data) {
  return request({
    url: '/blog/category',
    method: 'put',
    data: data
  })
}

// 删除文章分类管理
export function delCategory(id) {
  return request({
    url: '/blog/category/' + id,
    method: 'delete'
  })
}
