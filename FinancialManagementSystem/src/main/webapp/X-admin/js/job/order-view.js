//全局变量
var billId;
var typeId;
$(function () {
    billId = getBillId();
    typeId = getTypeId();

    var type;
    switch (typeId) {
        case '1':
            type = 'travel';
            break;
        case '2':
            type = 'welfare';
            break;
        case '3':
            type = 'traffic';
            break;
        case '4':
            type = 'property';
            break;
        default:
            break;
    }
    var data = {'billId':billId,'type':type};
    $.ajax({
        type:       'POST'
        ,url:       '../order/view.do'
        ,data:      data
        ,dataType:  'json'
        ,success:function (res){
            console.log(res);
            var baseInfo = res[0];//基本信息
            $("#trueName").empty();
            $("#trueName").html(baseInfo.trueName);
            $("#deptName").empty();
            $("#deptName").html(baseInfo.deptName);
            $("#phone").empty();
            $("#phone").html(baseInfo.phone);
            $("#typeName").empty();
            $("#typeName").html(baseInfo.typeName);
            $("#billId").empty();
            $("#billId").html(baseInfo.billId);
            $("#description").empty();
            $("#description").html(baseInfo.description);

            $("tbody").empty();
            var tbody = '';
            res[1].order.forEach(function (map) {//明细信息
                tbody += '<tr>'
                var size = getJsonSize(map);
                if (size==16){//国内差旅
                    tbody += '<td>'+map.date+'</td>'
                    tbody += '<td>'+map.location+'</td>'
                    tbody += '<td>'+map.transportation+'</td>'
                    tbody += '<td>'+map.countUser+'</td>'
                    tbody += '<td>'+map.foodCost+'</td>'
                    tbody += '<td>'+map.trafficCost+'</td>'
                    tbody += '<td>'+map.accomCost+'</td>'
                    tbody += '<td>'+map.otherCost+'</td>'
                    tbody += '<td>'+map.countCost+'</td>'
                }else if (size == 8){
                    tbody += '<td>'+map.useFor+'</td>'
                    tbody += '<td>'+map.depict+'</td>'
                    tbody += '<td>'+map.payeeUsername+'</td>'
                    tbody += '<td>'+map.bankCode+'</td>'
                    tbody += '<td>'+map.cost+'</td>'
                }
                tbody += '</tr>'
            })
            $("tbody").html(tbody);
            var baseURI = window.frameElement.baseURI;
            if (baseURI.indexOf('job-wait')>=0){
                var review = '<h2>审核</h2> ' +
                        '   <hr class="layui-bg-green"> ' +
                        '   <div class="layui-container"> ' +
                        '       <button type="button" class="layui-btn layui-btn-normal" onclick="pass()">通过</button> ' +
                        '       <button type="button" class="layui-btn layui-btn-danger" onclick="refuse()">退回</button> ' +
                        '   </div>';
                $("#review").html(review);
            }
        }
    })

})
//点击通过触发
function pass() {
    var val = 'pass';
    var remark = null;
    review(val,remark);
}

//点击退回账单时触发
function refuse() {
    var val = 'refuse';
    var remark;
    layer.prompt({title: '请输入退回理由，并确认', formType: 2}, function(text, index){
        layer.close(index);
        remark = text;
        review(val,remark);//执行下面的review()方法
    });
}

//审核
function review(val,remark) {
    $.ajax({
        type:       'POST'
        ,data:      {'billId':billId,'type':val,'remark':remark}
        ,url:       '../deal/review.do'
        ,dataType:  'json'
        ,success:function (res) {
            var code = res.code;
            console.log(code);

            if (code=='success'){//成功
                if (val=='pass'){//通过
                    layer.alert('通过审核成功', {
                        icon: 1,
                        skin: 'layer-ext-moon'
                    },function () {
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);//关闭当前页
                        parent.showPagebean(1,'../job/findWaitBill.do');//调用父页面方法,免刷新页面
                    })
                }
                if (val=='refuse'){//退回
                    layer.alert('退回账单成功', {
                        icon: 6,
                        skin: 'layer-ext-moon'
                    },function () {
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);//关闭当前页
                        parent.showPagebean(1,'../job/findWaitBill.do');//调用父页面方法,局部刷新页面
                    });
                }
            }
            if (code=='failure'){//失败
                layer.msg('操作失败');
            }
        }
    })
}

//计算json长度
function getJsonSize(json) {
    var size = 0;
    for(var i in json){
        size ++;
    }
    return size;
}

//接收billId
function getBillId(){
    var result;
    var url=window.location.search; //获取url中"?"符后的字串
    if(url.indexOf("?")!=-1){
        result = url.substr(url.indexOf("=")+1);
    }
    return result;
}
//接收typeId
function getTypeId() {
    var result;
    var url=window.location.pathname;
    var startIndex  = url.indexOf("view")+4;
    var endIndex    = url.indexOf(".do");
    result = url.substring(startIndex,endIndex);
    return result;
}