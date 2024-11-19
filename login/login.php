<?php
if (isset($_POST['email']) && isset($_POST['password'])) {
    require_once "conn.php";
    require_once "validate.php";

    $email = validate($_POST['email']);
    $password = validate($_POST['password']);

    // Prepare the SQL statement
    $stmt = $conn->prepare("SELECT * FROM user WHERE email=? AND password=?");
    $stmt->bind_param("ss", $email, $password);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
        echo "success";
    } else {
        echo "failure";
    }
    $stmt->close();
}

?>