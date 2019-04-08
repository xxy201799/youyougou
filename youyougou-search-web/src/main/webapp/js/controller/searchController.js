app.controller('searchController',function($scope,searchService){
	
	//搜索
	$scope.search=function(){
		alert(1);
		searchService.search($scope.searchMap).success(
			function(response){
				$scope.resultMap=response;				
			}
		);		
	}
	
	
});