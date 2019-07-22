function submit1(status){
    //var form = layui.form;
    $("input[name='Statusid']").attr("value",status)
    var data = $("#Form").serialize();
    $.ajax({
        type:'post',
        url:"../bill/orderinsert.do",
        async: false,
        data:data,  //重点必须为一个变量如：data
        dataType:'json',
        success:function(data){
            if (data>0) {
                layer.alert("保存账单成功!",function () {
                    location.href="../web/welcome.do";
                })

            }
            else{
                layer.alert("保存账单失败!");
            }
        },
        error:function(){
            alert("请求失败")
        }
    });
};
function update1(){
    var data = $("#Form").serialize();
    $.ajax({
        type:'post',
        url:"../bill/orderupdatesubmit.do",
        async: false,
        data:data,  //重点必须为一个变量如：data
        dataType:'json',
        success:function(data){
            if (data>0) {
                layer.alert("提交账单成功!",function () {
                    location.href="../bill/ordererror.do";
                });

            }
            else{
                layer.alert("提交账单失败!");
            }
        },
        error:function(){
            alert("请求失败")
        }
    });
};
function update2(){
    //var form = layui.form;
    var data = $("#Form").serialize();
    $.ajax({
        type:'post',
        url:"../bill/orderupdate.do",
        async: false,
        data:data,  //重点必须为一个变量如：data
        dataType:'json',
        success:function(data){
            if (data>0) {
                layer.alert("保存账单成功!",function () {
                    location.href="../bill/ordererror.do";
                })

            }
            else{
                layer.alert("保存账单失败!");
            }
        },
        error:function(){
            alert("请求失败")
        }
    });
};
    layui.use('upload',function(){
        var $ = layui.jquery
            ,upload = layui.upload;
        var demoListView = $('#demoList')
            ,uploadListIns = upload.render({
            elem: '#testList'
            , url: '../bill/uploadinvoice.do'
            , accept: 'file'
            , multiple: true
            , auto: false
            , data: 'json'
            , bindAction: '#uploadfile'
            , choose: function (obj) {
                var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                //读取本地文件
                obj.preview(function (index, file, result) {
                    var tr = $(['<tr id="upload-' + index + '">'
                        , '<td>' + file.name + '</td>'
                        , '<td>' + (file.size / 1014).toFixed(1) + 'kb</td>'
                        , '<td>等待上传</td>'
                        , '<td>'
                        , '<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
                        , '<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
                        , '</td>'
                        , '</tr>'].join(''));

                    //单个重传
                    tr.find('.demo-reload').on('click', function () {
                        obj.upload(index, file);
                    });

                    //删除
                    tr.find('.demo-delete').on('click', function () {
                        delete files[index]; //删除对应的文件
                        tr.remove();
                        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                    });

                    demoListView.append(tr);
                });
            }
            , done: function (data, index, upload) {
                console.log(data);
                console.log(data.res);
                if (data.res == 0) { //上传成功
                    $("input[name='invoice']").attr("value", data.invoice);
                    var tr = demoListView.find('tr#upload-' + index)
                        , tds = tr.children();
                    tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                    tds.eq(3).html(''); //清空操作
                    return delete this.files[index]; //删除文件队列已经上传成功的文件
                }
                this.error(index, upload);

            }


            ,error: function(index, upload){
                var tr = demoListView.find('tr#upload-'+ index)
                    ,tds = tr.children();
                tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
                tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
            }
        });
    });


