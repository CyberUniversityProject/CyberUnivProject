<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>이미지 업로드</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <h2>이미지 업로드</h2>
    <input id="imageInput" type="file" accept="image/*">
    <br><br>
    <button id="uploadBtn">업로드</button>

    <script>
        $(document).ready(function() {
            $('#uploadBtn').click(function() {
                var formData = new FormData();
                formData.append('imageFile', $('#imageInput')[0].files[0]);

                $.ajax({
                    url: '/api/imgUpload/uploadImage',
                    type: 'POST',
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function(response) {
                        alert('이미지가 성공적으로 업로드되었습니다.');
                        console.log(response);
                    },
                    error: function(xhr, status, error) {
                        alert('이미지 업로드에 실패했습니다.');
                        console.error(error);
                    }
                });
            });
        });
    </script>
</body>
</html>
