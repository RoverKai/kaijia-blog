import request from '@/utils/request'

// 查询浏览记录列表
export function listLog(query) {
  return request({
    url: '/blog/log/list',
    method: 'get',
    params: query
  })
}

// 查询浏览记录详细
export function getLog(id) {
  return request({
    url: '/blog/log/' + id,
    method: 'get'
  })
}

// 新增浏览记录
export function addLog(data) {
  return request({
    url: '/blog/log',
    method: 'post',
    data: data
  })
}

// 修改浏览记录
export function updateLog(data) {
  return request({
    url: '/blog/log',
    method: 'put',
    data: data
  })
}

// 删除浏览记录
export function delLog(id) {
  return request({
    url: '/blog/log/' + id,
    method: 'delete'
  })
}