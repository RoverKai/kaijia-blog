import request from '@/utils/request'

// 查询点赞管理列表
export function listArticleLike(query) {
  return request({
    url: '/blog/articleLike/list',
    method: 'get',
    params: query
  })
}

// 查询点赞管理详细
export function getArticleLike(articleId) {
  return request({
    url: '/blog/articleLike/' + articleId,
    method: 'get'
  })
}

// 新增点赞管理
export function addArticleLike(data) {
  return request({
    url: '/blog/articleLike',
    method: 'post',
    data: data
  })
}

// 修改点赞管理
export function updateArticleLike(data) {
  return request({
    url: '/blog/articleLike',
    method: 'put',
    data: data
  })
}

// 删除点赞管理
export function delArticleLike(articleId) {
  return request({
    url: '/blog/articleLike/' + articleId,
    method: 'delete'
  })
}