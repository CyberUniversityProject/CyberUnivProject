console.log("tuitionBill 페이지 진입 성공");
function printPage() {
	window.print();
}

let tuiAmount = document.getElementById('tuiAmount').value;
console.log(tuiAmount);
let schAmount = document.getElementById('schAmount').value;
console.log(schAmount);
var totalAmount = new Intl.NumberFormat().format(tuiAmount - schAmount);
console.log(totalAmount)
document.getElementById('totalAmount').textContent = totalAmount;


function generateMerchantUid() {
	// 랜덤한 10자리 숫자 생성
	return Math.floor(Math.random() * 10000000000).toString();
}

// IMP 객체 초기화
var IMP = window.IMP;
IMP.init("imp04766220");

var tuiInfoText = document.getElementById("tuiInfo").innerText;
var parts = tuiInfoText.split(" ");


// 결제 요청 함수
function requestPay() {

var tuiYear = tuiInfoText.match(/\d+/)[0];
var semester = parts[1].replace(/[^0-9]/g, '');
var buyerName = document.getElementById("buyer--name").innerText;

	$.ajax({
		url: "/buy/" + tuiYear +"/"+  semester,
		type: "GET",
		data: {
            tuiYear: tuiYear,
            semester: semester,
            buyerName: buyerName
        },
		success: function(paymentdto) {
			var buyer_name = paymentdto.buyerName;
			
			IMP.request_pay({
				pg: "kakaopay",
				pay_method: "card",
				merchant_uid: buyer_name + generateMerchantUid(), // 상점에서 생성한 고유 주문 번호
				name: "등록금 납부", // 상품명 또는 주문 내용
				amount: totalAmount, // 결제 금액 (원 단위)
				buyer_name: buyer_name, // 구매자 이름
				

			}, function (rsp) {
				if (rsp.success) {
					
					jQuery.ajax({
						url: "/buy/" + tuiYear +"/"+  semester,
						method: "POST",
						headers: { "Content-Type": "application/json" },
						data: {
							imp_uid: rsp.imp_uid,             // 결제 고유번호
							merchant_uid: rsp.merchant_uid,   // 주문번호
							amount: totalAmount,
							buyer_name: buyer_name							
						}
					}).done(function(data) {
						alert("결제가 완료되었습니다.");
						window.close();
						
					})
				} else {
					alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
				}
			});
		},
		error: function(xhr, status, error) {
            console.error("결제 정보를 받아오는 도중 오류가 발생했습니다:", error);
            // 오류 처리 로직 추가
        }
	});







}