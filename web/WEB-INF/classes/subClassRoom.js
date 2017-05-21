/**
 * Created by kingwen on 2016/10/3.
 */
$("#sub_classroom").click(function () {
    alert("提交数据")
   subClassInfo();

})

function subClassInfo() {

    var mbuilding=$("#id_building").val();
    var mroomnum=$("#id_roomnum").val();

    var data_info={
        "building":mbuilding,
        "mroomnum":mroomnum
    };
}