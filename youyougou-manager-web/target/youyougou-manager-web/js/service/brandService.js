/**
 * 品牌前端服务层
 */
app.service('brandService',function ($http) {
    //查询品牌列表服务
    this.findAll=function () {
        return  $http.get('../brand/findAll.do');
    }

    //品牌分页服务
    this.findPage=function (page,size) {
        return  $http.get('../brand/findPage.do?page='+page +'&size='+size);
    }

    //增加品牌服务
    this.add=function (entity) {
        return $http.post('../brand/add.do',entity);
    }
    //查询单个实体
    this.findOne=function (id) {
        return $http.get('../brand/findOne.do?id='+id);
    }
    //修改品牌服务
    this.update=function(entity){
        return $http.post('../brand/update.do',entity);
    }

    //删除品牌服务
    this.delete=function (ids) {
        return  $http.get('../brand/delete.do?ids='+ids);
    }

    //查询品牌服务
    this.search=function (page,rows,entity) {
        return $http.post('../brand/search.do?page='+page+'&size='+rows,entity);
    }
    //下拉表数据
    this.selectOptionList=function () {
        return $http.get("../brand/selectOptionList.do");
    }
});