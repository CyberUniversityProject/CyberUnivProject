$(document).ready(function() {
    // 모달 닫기 버튼을 클릭했을 때 모달이 닫히도록 설정
    $('#improvementsModal .close').click(function() {
        $('#improvementsModal').modal('hide');
    });

    // 모달의 바깥 영역을 클릭했을 때 모달이 닫히도록 설정
    $('#improvementsModal').click(function(event) {
        if ($(event.target).is('#improvementsModal')) {
            $('#improvementsModal').modal('hide');
        }
    });
   

    $('.improvements-content').click(function() {
        var improvements = $(this).text();
        $('#improvementsContent').text(improvements);
        $('#improvementsModal').modal('show');
    });
});