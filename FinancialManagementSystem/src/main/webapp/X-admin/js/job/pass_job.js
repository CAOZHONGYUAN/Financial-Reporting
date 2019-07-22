$(document).ready(function () {
    var url = '../job/findPassBill.do';
    showPagebean(1,url);//显示第一页
    //配置日期选择器
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#start', //指定元素
            trigger : 'click',
            type:   'datetime',
            format: 'yyyy-MM-dd HH:mm:ss'
        });
        laydate.render({
            elem: '#end', //指定元素
            trigger : 'click',
            type:   'datetime',
            format: 'yyyy-MM-dd HH:mm:ss'
        });
    });
    $('#search').on('click',function () {//search点击事件
        var url = '../job/findWaitBill.do';
        showPagebean(1,url);//显示第一页
    })
    $("body").keyup(function() {//回车事件
        if (event.keyCode == "13") {//keyCode=13是回车键
            $('#search').click();
        }
    });
})

//获取search参数
function getSearchParams() {
    var start   = $("#start").val();
    var end     = $("#end").val();
    var billId  = $("#billId").val();
    return {'start':start,'end':end,'billId':billId};
}
//显示数据
function showPagebean(index, url) {
    var params  = getSearchParams();
    var data    = {'pageIndex':index,'start':params.start,'end':params.end,'billId':params.billId};
    $.ajax({
        type:   'POST',
        url:    url,
        data: data,
        dataType:'json',
        success:function (pageBean) {
            console.log(pageBean);
            //解析总条数
            $("#totalRecords").empty();
            $("#totalRecords").html(pageBean.totalRecords);

            //解析页码导航
            var pb = '';
            var index = pageBean.pageIndex;
            if (pageBean.pageIndex!=1){
                pb += '<a class="head" href="javascript:void(0)" onclick="showPagebean(1,\''+ url +'\')">首页</a>'//首页
            }
            if (pageBean.isHavePrePage){
                index = pageBean.pageIndex-1;
                pb += '<a class="prev" href="javascript:void(0)" onclick="showPagebean( '+ index +',\''+ url +'\')">&lt;&lt;</a>'//上一页
            }
            if (pageBean.pageIndex-2>0){
                index = pageBean.pageIndex-2;
                pb += '<a class="num" href="javascript:void(0)" onclick="showPagebean( '+ index +',\''+ url +'\')">'+ index +'</a>'
            }
            if (pageBean.pageIndex-1>0){
                index = pageBean.pageIndex-1;
                pb += '<a class="num" href="javascript:void(0)" onclick="showPagebean( '+ index +',\''+ url +'\')">'+ index +'</a>'
            }
            if (pageBean.totalPages>1){
                index = pageBean.pageIndex;
                pb += '<span class="current">'+ index +'</span>'//当前页
            }
            if (pageBean.pageIndex+1<=pageBean.totalPages){
                index = pageBean.pageIndex+1;
                pb += '<a class="num" href="javascript:void(0)" onclick="showPagebean( '+ index +',\''+ url +'\')">'+ index +'</a>'
            }
            if (pageBean.pageIndex+2<=pageBean.totalPages){
                index = pageBean.pageIndex+2;
                pb += '<a class="num" href="javascript:void(0)" onclick="showPagebean( '+ index +',\''+ url +'\')">'+ index +'</a>'
            }
            if (pageBean.isHaveNextPage){
                index = pageBean.pageIndex+1;
                pb += '<a class="next" href="javascript:void(0)" onclick="showPagebean( '+ index +',\''+ url +'\')">&gt;&gt;</a>'//下一页
            }
            if (pageBean.pageIndex!=pageBean.totalPages) {
                pb += '<a class="tail" href="javascript:void(0)" onclick="showPagebean( '+ pageBean.totalPages +',\''+ url +'\')">尾页</a>'//尾页
            }
            $("#pageBean").empty();
            $("#pageBean").html(pb);

            //显示表格数据
            var tBody = '';
            pageBean.pageDatas.forEach(function (map) {
                var view = '../order/view'+map.typeId+'.do?billId='+ map.billId;
                tBody += '      <tr> ' +
                    '            <td><a onclick="x_admin_show(\'账单审批\',\''+view+'\')" href="javascript:;">'+ map.billId +'</a></td> ' +
                    '            <td>'+map.trueName+'</td> ' +
                    '            <td>'+map.cost+'</td> ' +
                    '            <td>'+map.submitDatetime+'</td> ' +
                    '            <td><font color="#1AA093">已审核</font></td> ' +
                    '          </tr>';
            })
            $("tbody").empty();
            $("tbody").html(tBody);
        }
    })
}