<?php
require_once "conn.php";
require_once "validate.php";

if(isset($_POST['date']) && isset($_POST['time'])){
    $date = validate($_POST['date']);
    $time = validate($_POST['time']);
    $userId = 1; // Replace with dynamic user ID

    $sql = "INSERT INTO appointmenttb (pid, ID, apptime) VALUES (NULL, '$userId', '$time')";
    
    if ($conn->query($sql) === TRUE) {
        echo "success";
    } else {
        echo "failure";
    }
}
?>
