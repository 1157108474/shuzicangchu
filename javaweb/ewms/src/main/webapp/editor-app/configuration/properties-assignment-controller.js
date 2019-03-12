/*
 * Activiti Modeler component part of the Activiti project
 * Copyright 2005-2014 Alfresco Software, Ltd. All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

/*
 * Assignment
 */
var KisBpmAssignmentCtrl = ['$scope', '$modal', function ($scope, $modal) {

    // Config for the modal window
    var opts = {
        template: 'editor-app/configuration/properties/assignment-popup.html?version=' + Date.now(),
        scope: $scope
    };

    // Open the dialog
    $modal(opts);
}];

var KisBpmAssignmentPopupCtrl = ['$scope', '$modal', function ($scope, $modal) {
    // Put json representing assignment on scope
    if ($scope.property.value !== undefined && $scope.property.value !== null
        && $scope.property.value.assignment !== undefined
        && $scope.property.value.assignment !== null) {
        $scope.assignment = $scope.property.value.assignment;
    } else {
        $scope.assignment = {};
    }

    if ($scope.assignment.candidateUsers == undefined || $scope.assignment.candidateUsers.length == 0) {
        $scope.assignment.candidateUsers = [{value: ''}];
    }

    // Click handler for + button after enum value
    var userValueIndex = 1;
    $scope.addCandidateUserValue = function (index) {
        $scope.assignment.candidateUsers.splice(index + 1, 0, {value: 'value ' + userValueIndex++});
    };

    // Click handler for - button after enum value
    $scope.removeCandidateUserValue = function (index) {
        $scope.assignment.candidateUsers.splice(index, 1);
    };

    if ($scope.assignment.candidateGroups == undefined || $scope.assignment.candidateGroups.length == 0) {
        $scope.assignment.candidateGroups = [{value: ''}];
    }

    var groupValueIndex = 1;
    $scope.addCandidateGroupValue = function (index) {
        $scope.assignment.candidateGroups.splice(index + 1, 0, {value: 'value ' + groupValueIndex++});
    };

    // Click handler for - button after enum value
    $scope.removeCandidateGroupValue = function (index) {
        $scope.assignment.candidateGroups.splice(index, 1);
    };

    //Open the dialog to select users
    $scope.choseAssignment = function (flag) {
        var opts = {
            template: 'editor-app/configuration/properties/assignment-popup-popup.html?version=' + Date.now(),
            scope: $scope
        };
        $scope.choseAssignmentFlag = flag;
        // Open the dialog
        $modal(opts);
    }

    //Open the dialog to select candidateGroups修改为权限按钮选择
    $scope.choseCandidateGroups = function () {
        var opts = {
            template: 'editor-app/configuration/properties/assignment-choosePermission.html?version=' + Date.now(),
            scope: $scope
        };
        // Open the dialog
        $modal(opts);
    }
    $scope.chosePermission = function () {
        var opts = {
            template: 'editor-app/configuration/properties/assignment-candidateGroup.html?version=' + Date.now(),
            scope: $scope
        };
        // Open the dialog
        $modal(opts);
    }
    $scope.save = function () {

        $scope.property.value = {};
        handleAssignmentInput($scope);
        $scope.property.value.assignment = $scope.assignment;
        $scope.updatePropertyInModel($scope.property);
        $scope.close();
    };

    // Close button handler
    $scope.close = function () {
        handleAssignmentInput($scope);
        $scope.property.mode = 'read';
        $scope.$hide();
    };

    var handleAssignmentInput = function ($scope) {
        if ($scope.assignment.candidateUsers) {
            var emptyUsers = true;
            var toRemoveIndexes = [];
            for (var i = 0; i < $scope.assignment.candidateUsers.length; i++) {
                if ($scope.assignment.candidateUsers[i].value != '') {
                    emptyUsers = false;
                }
                else {
                    toRemoveIndexes[toRemoveIndexes.length] = i;
                }
            }

            for (var i = 0; i < toRemoveIndexes.length; i++) {
                $scope.assignment.candidateUsers.splice(toRemoveIndexes[i], 1);
            }

            if (emptyUsers) {
                $scope.assignment.candidateUsers = undefined;
            }
        }

        if ($scope.assignment.candidateGroups) {
            var emptyGroups = true;
            var toRemoveIndexes = [];
            for (var i = 0; i < $scope.assignment.candidateGroups.length; i++) {
                if ($scope.assignment.candidateGroups[i].value != '') {
                    emptyGroups = false;
                }
                else {
                    toRemoveIndexes[toRemoveIndexes.length] = i;
                }
            }

            for (var i = 0; i < toRemoveIndexes.length; i++) {
                $scope.assignment.candidateGroups.splice(toRemoveIndexes[i], 1);
            }

            if (emptyGroups) {
                $scope.assignment.candidateGroups = undefined;
            }
        }
    };
    $scope.$on('choseAssigneesStr', function (event, data) {
        $scope.assignment.candidateUsers[0].value = data;
    });
    $scope.$on('choseAssigneeStr', function (event, data) {
        $scope.assignment.assignee = data;
    });
    $scope.$on('choseAssigneesStrName', function (event, data) {
        $scope.assignment.candidateUsers[0].valueName = data;
    });
    $scope.$on('choseAssigneeStrName', function (event, data) {
        $scope.assignment.assigneeName = data;
    });
    $scope.$on('choseCandidateGroupsStr', function (event, data) {
        $scope.assignment.candidateGroups[0].value = data;
    });
    $scope.$on('choseCandidateGroupsNameStr', function (event, data) {
        $scope.assignment.candidateGroups[0].valueName = data;
    });

}];
var KisBpmChoseAssignmentCtrl = ['$scope', '$http', function ($scope, $http) {
    //var roles = [];
    var roleId;
    //配置
    $scope.count = 0;
    $scope.p_pernum = 7;
    //变量
    $scope.p_current = 1;
    $scope.p_all_page = 0;
    $scope.pages = [];
    //
    $scope.count_role = 0;
    $scope.p_pernum_role = 6;
    $scope.p_current_role = 1;
    $scope.p_all_page_role = 0;
    $scope.pages_role = [];

    //$scope.formData.assignee
    //$scope.accounts
    var chooseUsers = [];//被选中的user
    //初始化第一页
    $scope.getAllRoles = function (successCallback) {
        //获取前页数据
        if ($scope.property.value !== undefined && $scope.property.value !== null
            && $scope.property.value.assignment !== undefined
            && $scope.property.value.assignment !== null) {
            $scope.assignment = $scope.property.value.assignment;
            
            
        } else {
            $scope.assignment = {};
        }
       if($scope.assignment.candidateUsers != undefined && $scope.assignment.candidateUsers.length != 0){
    	var userCodes =   $scope.assignment.candidateUsers[0].value;
    	var userNames =  $scope.assignment.candidateUsers[0].valueName;
    	console.log(userCodes);
    	console.log(userNames);
    	userCodes = userCodes.split(",");
    	userNames = userNames.split(",");
    	
    	var i = userCodes.length;
    	while (i--) {
    		chooseUsers.push({code: userCodes[i], name: userNames[i]});
    	}
    	console.log($scope.assignment);
    	console.log(userCodes);
    	console.log(userNames);
    	console.log(chooseUsers);
    	
    }
        $http.get(projectName + "/activitipart/getRole.htm").success(function (res) {
            for (var i = 0; i < res.length; i++) {
                if (i == 0) {
                    roleId = res[i].roleCode;
                    $scope.p_index_role(res[i].roleCode);
                }
            }
        });
    };


    $scope.getAllRoles(function () {
    });
    var _get_role = function (page, size, callback, roleId) {
        $http.get(projectName + "/activitipart/gotoRole.htm?status=0&page=" + page + "&size=" + size).success(function (res) {
            if (res && res[0].status == 1) {
                $scope.count_role = res[0].count;
                var obj = res[0].list;
                if (res[0].list != null) {
                    var roles = [];
                    for (var i = 0; i < obj.length; i++) {
                        if (i == 0) {
                            res[i].roleCode = obj[i].roleCode;
                            $scope.p_index(obj[i].roleCode);
                        }
                        roles.push({id: obj[i].roleCode, name: obj[i].roleName});
                    }

                    $scope.roles = roles;
                }
                $scope.p_current_role = page;
                $scope.p_all_page_role = Math.ceil($scope.count_role / $scope.p_pernum_role);
                reloadPnoRole();
                callback();
            } else {
                alert("加载失败");
            }
        });
    }
    var accountAll = [];//为了统计所有人员，目的展示姓名
    //获取数据
    var _get = function (page, size, callback, roleId) {

        $http.get(projectName + "/activitipart/getUserByRole.htm?status=0&page=" + page + "&size=" + size + "&roleId=" + roleId).success(function (res) {
            if (res && res[0].status == 1) {
                $scope.count = res[0].count;
                var obj = res[0].list;
                if (res[0].list != null) {
                    var accounts = [];
                    for (var i = 0; i < obj.length; i++) {
                        accountAll.push({id: obj[i].id, code: obj[i].code, name: obj[i].name, index: i});
                        accounts.push({id: obj[i].id, code: obj[i].code, name: obj[i].name, index: i});
                    }
                    $scope.accounts = accounts;
                    accountAll = unique(accountAll);//去重
                    console.log(accounts);
                    console.log(chooseUsers);

                    for (var i = 0; i < accounts.length; i++) {//选中之前选中的人员
                        if (isInArray(chooseUsers, accounts[i])) {
                            accounts[i].selected = true;
                        } else {
                            accounts[i].selected = false;
                        }
                    }
                }
                $scope.p_current = page;
                $scope.p_all_page = Math.ceil($scope.count / $scope.p_pernum);
                reloadPno();
                callback();
            } else {
                alert("加载失败");
            }
        });
    }
    var unique = function (array) {//去重
        var r = [];
        for (var i = 0, l = array.length; i < l; i++) {
            for (var j = i + 1; j < l; j++)
                if (array[i] === array[j]) j = ++i;
            r.push(array[i]);
        }
        return r;
    }

    //首页
    $scope.p_index = function () {
        $scope.load_page(1, roleId);
    }
    $scope.p_index_role = function (roleId) {
        $scope.load_page_role(1, roleId);
    }
    //尾页
    $scope.p_last = function () {
        $scope.load_page($scope.p_all_page);
    }
    $scope.p_last_role = function () {
        $scope.load_page_role($scope.p_all_page_role);
    }
    //加载某一页
    $scope.load_page = function (page) {
       /* TODO checkUser();*/
        _get(page, $scope.p_pernum, function () {
        }, roleId);
    };
    $scope.load_page_role = function (page, roleId) {
        _get_role(page, $scope.p_pernum_role, function () {
        }, roleId);
    };
    //初始化页码
    var reloadPno = function () {
        $scope.pages = calculateIndexes($scope.p_current, $scope.p_all_page, 8);
    };
    var reloadPnoRole = function () {
        $scope.pages_role = calculateIndexes($scope.p_current_role, $scope.p_all_page_role, 8);
    };
//分页算法
    var calculateIndexes = function (current, length, displayLength) {
        var indexes = [];
        var start = Math.round(current - displayLength / 2);
        var end = Math.round(current + displayLength / 2);
        if (start <= 1) {
            start = 1;
            end = start + displayLength - 1;
            if (end >= length - 1) {
                end = length - 1;
            }
        }
        if (end >= length - 1) {
            end = length;
            start = end - displayLength + 1;
            if (start <= 1) {
                start = 1;
            }
        }
        for (var i = start; i <= end; i++) {
            indexes.push(i);
        }
        return indexes;
    };

    $scope.getAllAccountByRole = function (i, value) {
        $scope.focus = i;

        //重置选择的人员
        /*accountAll = [];//重置该角色下的所有人员
        chooseUsers = [];//对已经选中的人员重置*/
        roleId = value;
        $scope.p_index();
    };

    //单选时获取信息
    $scope.selectRadio = function (account) {
        //更新全部的数据
        accountAll = contains(accountAll,account,1);
        //更新选中的数据
        chooseUsers = contains(chooseUsers,account,2);
    };
    //更新信息
    function contains(arr, account,type) {
        var bool = false;
        var i = arr.length;
        var arrAll = arr;
        while (i--) {
            acc = arr[i];
            if(type==1){
                if (acc.id == account.id ) {
                    var arrs = []
                    for (var k= 0; k < arrAll.length; k++) {
                        if(arrAll[k].id != acc.id ){
                            arrs.push(arr[k]);
                        }
                    }
                    bool  = true;
                    arrAll = arrs;
                }
            }else {
                if (acc.code == account.code ) {
                    var arrs = []
                    for (var k= 0; k < arrAll.length; k++) {
                        if(arrAll[k].code != acc.code ){
                            arrs.push(arr[k]);
                        }
                    }
                    bool  = true;
                    arrAll = arrs;
                }
            }
        }
        if(bool){
            return arrAll;
        }else {
            if(type==1){
                arr.push({id: account.id, code: account.code, name: account.name, index: arr.length + 1});
            }else {
                arr.push({code: account.code, name: account.name});
            }
            return arr ;
        }
    }

    // Close button handler
    $scope.close = function () {
        $scope.$hide();
    };
    $scope.formData = {};
    $scope.candidateUser = {};

    //Save Data
    $scope.save = function () {
        if ($scope.choseAssignmentFlag == "assignee") {
            var choseAssignee = $scope.formData.assignee;
            var accountsName = "";
            for (var i = 0; i < accountAll.length; i++) {
                if (accountAll[i].code == choseAssignee) {
                    accountsName = accountAll[i].name;
                }
            }
            //$scope.accounts=accounts;
            // $scope.$emit('choseAssigneeStrName', choseAssignee);
            $scope.$emit('choseAssigneeStr', choseAssignee);
            $scope.$emit('choseAssigneeStrName', accountsName);
        } else if ($scope.choseAssignmentFlag == "assignees") {
            var choseAssignees;
            var choseAssigneesStr = "";
            var choseAssigneesStrName = "";
            /*TODO checkUser();*/
//            $scope.property.value=$scope;
//            $scope.property.value.assignment=$scope.assignment;
            choseAssignees = chooseUsers;
            for (var i = 0; i < choseAssignees.length; i++) {
                choseAssigneesStr += choseAssignees[i].code + ",";
                choseAssigneesStrName += choseAssignees[i].name + ",";
            }
            /* if(chooseUsers.length>0){
                choseAssignees = chooseUsers;
                 for (var i=0;i<choseAssignees.length; i++) {
                     choseAssigneesStr += choseAssignees[i].code + ",";
                     choseAssigneesStrName += choseAssignees[i].name + ",";
                 }
             }else{
                choseAssignees =$scope.accounts;
                 for (var i=0;i<choseAssignees.length; i++) {
                     if (choseAssignees[i].selected) {
                         choseAssigneesStr += choseAssignees[i].code + ",";
                         choseAssigneesStrName += choseAssignees[i].name + ",";
                     }
                 }
             }*/
            choseAssigneesStr = choseAssigneesStr.substring(0, choseAssigneesStr.length - 1);
            choseAssigneesStrName = choseAssigneesStrName.substring(0, choseAssigneesStrName.length - 1);
            $scope.$emit('choseAssigneesStr', choseAssigneesStr);
            $scope.$emit('choseAssigneesStrName', choseAssigneesStrName);
        }
        $scope.close();
    };
    $scope.selectAll = function ($event) {
        var checkbox = $event.target;
        var choseAssignees = $scope.accounts;
        for (var i = 0; i < choseAssignees.length; i++) {
            if (checkbox.checked) {
                choseAssignees[i].selected = true;
            } else {
                choseAssignees[i].selected = false;
            }
        }
        $scope.accounts = choseAssignees;
    }
    var checkUser = function () {
        if ($scope.choseAssignmentFlag == "assignee") {
        } else if ($scope.choseAssignmentFlag == "assignees") {
            var choseAssignees = $scope.accounts;
            if (choseAssignees != undefined) {
                for (var i = 0; i < choseAssignees.length; i++) {
                    if (choseAssignees[i].selected) {
                        if (!(isInArray(chooseUsers, choseAssignees[i]))) {
                            chooseUsers.push(choseAssignees[i]);
                        }
                    } else {
                        removeArray(chooseUsers, choseAssignees[i]);
                    }
                }
            }
        }
    }

    //判断数组元素是否存在
    var isInArray = function (arr, value) {
        for (var i = 0; i < arr.length; i++) {
            if (value.code == arr[i].code) {
                return true;
            }
        }
        return false;
    }
    var removeArray = function (arr, value) {
        for (var i = arr.length - 1; i >= 0; i--) {
            if (arr[i].code == value.code) {
                arr.splice(i, 1);
            }
        }
    }
}];


//权限选择页面
var KisBpmChosePermissionCtrl = ['$scope', '$http', function ($scope, $http) {
    var permissions = [];
    //var initId;
    //var pathName = window.document.location.pathname;
    //var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    $scope.getAllPermission = function (successCallback) {
        $http({
            method: 'get',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            url: projectName + '/activitipart/getPermission.htm'
        })
            .success(function (data, status, headers, config) {
                var obj = data;
                for (var i = 0; i < obj.length; i++) {
                    permissions.push({id: obj[i].id, name: obj[i].name});
                }
                $scope.permissions = permissions;
            })
            .error(function (data, status, headers, config) {
            });
    };
    $scope.getAllPermission(function () {
    });
    $scope.close = function () {
        $scope.$hide();
    };
    //Save Data
    $scope.save = function () {
        var permissions = $scope.permissions;
        var permissionsStr = "";
        var permissionsNameStr = "";
        for (var i = 0; i < permissions.length; i++) {
            if (permissions[i].selected) {
                permissionsStr += permissions[i].id + ",";
                permissionsNameStr += permissions[i].name + ",";
            }
        }
        permissionsStr = permissionsStr.substring(0, permissionsStr.length - 1);
        $scope.$emit('choseCandidateGroupsStr', permissionsStr);
        $scope.$emit('choseCandidateGroupsNameStr', permissionsNameStr);
        $scope.close();
    };

}];