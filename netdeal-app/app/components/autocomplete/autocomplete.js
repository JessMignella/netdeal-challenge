app.directive('autocomplete', function($timeout) {
    return {
        restrict: 'E',
        scope: {
            items: '=',
            model: '='
        
        },
        templateUrl: 'components/autocomplete/autocomplete.html',
        link: function(scope, element, attrs) {
            scope.selectedItem = null;
            scope.showDropdown = false;
            scope.query = '';

            scope.selectItem = function(item) {
                scope.model = item;
                scope.query = item.titulo || item.nome || item;
                scope.showDropdown = false;
            };

            scope.$watch('query', function(newVal) {
                if (!newVal) {
                    scope.model = null;
                } else {
                    var matchingItems = scope.items.filter(function(item) {
                        if(item.titulo){
                        return  item.titulo.toLowerCase().indexOf(newVal.toLowerCase()) !== -1;
                        } else return item.nome && item.nome.toLowerCase().indexOf(newVal.toLowerCase()) !== -1;
                    });
                    scope.showDropdown = matchingItems.length > 0 && !scope.model;
                }

                // Check if query matches any item in the list
                var isValid = scope.items.some(function(item) {
                    return item.titulo && item.titulo.toLowerCase() === newVal.toLowerCase();
                });

                // If not valid, clear the model and query
                if (!isValid && newVal && !scope.showDropdown) {
                    $timeout(function() {
                        scope.model = null;
                        scope.query = '';
                    }, 200);
                }
            });

            scope.filteredItems = function() {
                return scope.items.filter(function(item) {
                    return item.titulo && item.titulo.toLowerCase().indexOf(scope.query.toLowerCase()) !== -1;
                });
            };

            element.find('input').on('focus', function() {
                $timeout(function() {
                    scope.showDropdown = true;
                });
            });

            element.find('input').on('blur', function() {
                $timeout(function() {
                    scope.showDropdown = false;
                }, 200);
            });
        }
    };
});