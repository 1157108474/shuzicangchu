layui.define(['element', 'common'], function (exports) {
    "use strict";
    var $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        element = layui.element,
        common = layui.common,
        cacheName = 'tb_navbar';

    var Navbar = function () {
        /**
         *  默认配置
         */
        this.config = {
            elem: undefined, //容器
            data: undefined, //数据源
            url: undefined, //数据源地址
            type: 'GET', //读取方式
            cached: false, //是否使用缓存
            spreadOne: false //设置是否只展开一个二级菜单
        };
        this.v = '1.0.0';
    };
    //渲染
    Navbar.prototype.render = function () {
        var _that = this;
        var _config = _that.config;
        if (typeof (_config.elem) !== 'string' && typeof (_config.elem) !== 'object') {
            common.throwError('Navbar error: elem参数未定义或设置出错，具体设置格式请参考文档API.');
        }
        var $container;
        if (typeof (_config.elem) === 'string') {
            $container = $('' + _config.elem + '');
        }
        if (typeof (_config.elem) === 'object') {
            $container = _config.elem;
        }
        if ($container.length === 0) {
            common.throwError('Navbar error:找不到elem参数配置的容器，请检查.');
        }
        if (_config.data === undefined && _config.url === undefined) {
            common.throwError('Navbar error:请为Navbar配置数据源.')
        }
        if (_config.data !== undefined && typeof (_config.data) === 'object') {
            var html = getHtml(_config.data);
            $container.html(html);
            element.init();
            _that.config.elem = $container;
        } else {
            if (_config.cached) {
                var cacheNavbar = layui.data(cacheName);
                if (cacheNavbar.navbar === undefined) {
                    $.ajax({
                        type: _config.type,
                        url: _config.url,
                        async: false, //_config.async,
                        dataType: 'json',
                        success: function (result, status, xhr) {
                            //添加缓存
                            layui.data(cacheName, {
                                key: 'navbar',
                                value: result
                            });
                            var html = getHtml(result);
                            $container.html(html);
                            element.init();
                        },
                        error: function (xhr, status, error) {
                            common.msgError('Navbar error:' + error);
                        },
                        complete: function (xhr, status) {
                            _that.config.elem = $container;
                        }
                    });
                } else {
                    var html = getHtml(cacheNavbar.navbar);
                    $container.html(html);
                    element.init();
                    _that.config.elem = $container;
                }
            } else {
                //清空缓存
                layui.data(cacheName, null);
                $.ajax({
                    type: _config.type,
                    url: _config.url,
                    async: false, //_config.async,
                    dataType: 'json',
                    success: function (result, status, xhr) {
                        var html = getHtml(result);
                        $container.html(html);
                        element.init();
                    },
                    error: function (xhr, status, error) {
                        common.msgError('Navbar error:' + error);
                    },
                    complete: function (xhr, status) {
                        _that.config.elem = $container;
                    }
                });
            }
        }

        //只展开一个二级菜单
        if (_config.spreadOne) {
            var $ul = $container.children('ul');
            $ul.find('li.layui-nav-item').each(function () {
                $(this).on('click', function () {
                    $(this).siblings().removeClass('layui-nav-itemed');
                });
            });
        }
        return _that;
    };
    /**
     * 配置Navbar
     * @param {Object} options
     */
    Navbar.prototype.set = function (options) {
        var that = this;
        that.config.data = undefined;
        $.extend(true, that.config, options);
        return that;
    };
    /**
     * 绑定事件
     * @param {String} events
     * @param {Function} callback
     */
    Navbar.prototype.on = function (events, callback) {
        var that = this;
        var _con = that.config.elem;
        if (typeof (events) !== 'string') {
            common.throwError('Navbar error:事件名配置出错，请参考API文档.');
        }
        var lIndex = events.indexOf('(');
        var eventName = events.substr(0, lIndex);
        var filter = events.substring(lIndex + 1, events.indexOf(')'));
        if (eventName === 'click') {
            if (_con.attr('lay-filter') !== undefined) {
                _con.children('ul').find('li').each(function () {
                    var $this = $(this);
                    if ($this.find('dl').length > 0) {
                        var $dd = $this.find('dd').each(function () {
                            $(this).on('click', function () {
                                var $a = $(this).children('a');
                                var href = $a.data('url');
                                var icon = $a.children('i:first').data('icon');
                                var title = $a.children('cite').text();
                                var data = {
                                    elem: $a,
                                    field: {
                                        href: href,
                                        icon: icon,
                                        title: title
                                    }
                                };
                                callback(data);
                            });
                        });
                    } else {
                        $this.on('click', function () {
                            var $a = $this.children('a');
                            var href = $a.data('url');
                            var icon = $a.children('i:first').data('icon');
                            var title = $a.children('cite').text();
                            var data = {
                                elem: $a,
                                field: {
                                    href: href,
                                    icon: icon,
                                    title: title
                                }
                            };
                            callback(data);
                        });
                    }
                });
            }
        }
    };
    /**
     * 清除缓存
     */
    Navbar.prototype.cleanCached = function () {
        layui.data(cacheName, null);
    };

    /**
     * 获取html字符串
     * @param {Object} data
     */
    function getHtml(data) {
        //debugger;
        var ulHtml = '<ul class="layui-nav layui-nav-tree beg-navbar">';

        function getDl(dl) {
            var dlHtml = '<dl class="layui-nav-child" style="filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#4c000000,endColorstr=#4c000000); ">';

            for (var l = 0; l < dl.length; l++) {
                dlHtml += getDd(dl[l]);
                if (dl[l].children) {
                    dlHtml += getLi(dl[l].children)
                }
            }
            dlHtml += "</dl>";
            return dlHtml;
        }

        function getDd(dd) {
            var ddHtml = '<dd title="' + dd.title + '">';
            ddHtml += '<a href="javascript:;" data-url="' + dd.href + '">';
            if (dd.icon) {
                ddHtml += '<i class="fa ' + dd.icon + '" data-icon="' + dd.icon + '" aria-hidden="true"></i>';
            }
            ddHtml += '<cite>' + dd.title + '</cite>';
            ddHtml += '</a></dd>';
            return ddHtml;
        }

        function getLi(data) {
            var result = "";
            for (var i = 0; i < data.length; i++) {
                var liHtml = '<li class="layui-nav-item ">';
                liHtml += '<a href="javascript:;">';

                var li = data[i];

                if (li.icon !== undefined && li.icon !== '') {
                    if (li.icon.indexOf('fa-') !== -1) {
                        liHtml += '<i class="fa ' + li.icon + '" aria-hidden="true" data-icon="' + li.icon + '"></i>';
                    } else {
                        liHtml += '<i class="layui-icon" data-icon="' + li.icon + '">' + li.icon + '</i>';
                    }
                }
                liHtml += '<cite>' + li.title + '</cite>';
                liHtml += '</a>';

                // 处理子元素
                if (li.children) {
                    liHtml += getDl(li.children);
                }

                liHtml += '</li>';

                result += liHtml;
            }
            return result;
        }

        ulHtml += getLi(data);

        ulHtml += '</ul>';

        return ulHtml;
    }

    var navbar = new Navbar();

    exports('navbar', function (options) {
        return navbar.set(options);
    });
})
;