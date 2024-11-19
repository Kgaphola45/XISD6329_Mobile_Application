<?php
if(isset($_POST['ID']) && isset($_POST['appdate']) && isset($_POST['apptime'])){
    require_once "conn.php";
    require_once "validate.php";

    $ID = validate($_POST['ID']);
    $appdate = validate($_POST['appdate']);
    $apptime = validate($_POST['apptime']);

    $sql = "INSERT INTO appointmenttb (ID, appdate, apptime) VALUES ('$ID', '$appdate', '$apptime')";

    if($conn->query($sql) === TRUE){
        echo "success";
    } else {
        echo "failure";
    }
} else {
    echo "failure";
}
?>
