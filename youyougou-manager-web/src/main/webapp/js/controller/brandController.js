/**
 * 品牌前端控制层
 */
app.controller('brandController',function($scope,$http,$controller,brandService) {

    $controller('baseController',{$scope:$scope});

    //查询品牌列表
    $scope.findAll = function () {
        brandService.findAll().success(
            function (response) {
                $scope.list = response;//给列表变量赋值
            }
        );
    }

    //分页
    $scope.findPage=function(page,size){
        brandService.findPage(page,size).success(
            function(response){
                $scope.list=response.rows;//显示当前页数据
                $scope.paginationConf.totalItems=response.total;//更新总记录数
            }
        );
    }

    //增加
    $scope.save=function () {
        var object=null;

        if($scope.entity.id!=null){
            object=brandService.update($scope.entity);//执行修改方法
        }else {
            object=brandService.add($scope.entity);
        }

        object.success(
            function (response) {
                if(response.success){
                    //重新加载
                    $scope.reloadList();
                }else {
                    alert(response.message);
                }
            }
        );
    }
    $scope.findOne=function(id){
        brandService.findOne(id).success(
            function (response) {
                $scope.entity=response;
            }
        );
    }


    //批量删除
    $scope.delete=function () {
        //获取选中的复选框
        brandService.delete($scope.selectIds).success(
            function(response){
                if(response.success){
                    $scope.reloadList();//刷新列表
                }
            }
        );
    }

    //定义查询实体
    $scope.searchEntity={};
    //条件查询
    $scope.search=function (page,size) {
        brandService.search(page,size,$scope.searchEntity).success(
            function (response) {
                $scope.paginationConf.totalItems=response.total;//总记录数
                $scope.list=response.rows;//给列表变量赋值
            }
        );
    }
});