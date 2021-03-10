layui.use(['layer', 'util'], function(){ //独立版的layer无需执行这一句
    var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
    var util = layui.util;

    //触发事件
    var active = {
        notice: function(){
            //示范一个公告层
            layer.open({
                type: 1
                ,title: false //不显示标题栏
                ,closeBtn: false
                ,area: '300px;'
                ,shade: 0.8
                ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
                ,btn: ['确认修改', '取消']
                ,btnAlign: 'c'
                ,moveType: 1 //拖拽模式，0或者1
                ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;"><form id="change_password"><input type="password" name="password_old" id="password_old" lay-verify="pass" autocomplete="off" placeholder="请验证原始密码" class="layui-input"  style="color: #000000"><br><br><input type="password" name="password_new" id="password_new" lay-verify="pass" autocomplete="off" placeholder="请输入新密码" class="layui-input" style="color: #000000"><br><br><input type="password" name="password_con" id="password_con" lay-verify="pass" autocomplete="off" placeholder="请确认密码" class="layui-input" style="color: #000000"></form></div>'
                ,success: function(layero){
                    var btn = layero.find('.layui-layer-btn');
                    btn.find('.layui-layer-btn0').click(function() {
                        var password_old = util.escape($("#password_old").val());
                        var password_new = util.escape($("#password_new").val());
                        var password_con = util.escape($("#password_con").val());
                        if (password_new != password_con) {
                            layer.alert("确认密码不一致");
                            return
                        }
                        if (!password_new.match(/^[\S]{6,12}$/)) {
                            layer.alert("密码必须6到12位，且不能出现空格");
                            return
                        }
                        $.ajax({
                            type:'POST',
                            url:'/login/modpwd',
                            data:{password_old:password_old, password_new:password_new},
                            success:function(data){
                                if (data == '0') {
                                    layer.alert("修改失败，可能是原始密码验证错误！");
                                    return
                                }
                                layer.alert("修改成功！");
                            },
                            error:function() {
                                layer.alert("修改失败！");
                            }
                        });
                    })
                }
            });
        }
    }

    $('#passwd').on('click', function(){
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });

});