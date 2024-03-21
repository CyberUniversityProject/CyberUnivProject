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
		url: "/buy/" + tuiYear + "/" + semester,
		type: "GET",
		data: {
			tuiYear: tuiYear,
			semester: semester,
			buyerName: buyerName,
			schAmount: schAmount
		},
		success: function(paymentdto) {
			var buyer_name = paymentdto.buyerName;
			var tuiYear = paymentdto.tuiYear;
			var semester = paymentdto.semester;
			var schAmount = paymentdto.schAmount;
			IMP.request_pay({
				pg: "kakaopay",
				pay_method: "card",
				merchant_uid: buyer_name + "_" + generateMerchantUid(), // 상점에서 생성한 고유 주문 번호
				name: "등록금 납부", // 상품명 또는 주문 내용
				amount: totalAmount, // 결제 금액 (원 단위)
				buyer_name: buyer_name, // 구매자 이름


			}, function(rsp) {
				if (rsp.success) {
					var userData = {
						"impUid": rsp.imp_uid,
						"merchantUid": rsp.merchant_uid,
						"buyerName": buyer_name,
						"schAmount": schAmount,

					};
					console.log("impUid: ", userData.impUid);
					console.log("merchantUid: ", userData.merchantUid);
					console.log("buyerName: ", userData.buyerName);
					console.log("schAmount: ", userData.schAmount);

					jQuery.ajax({
						url: "/buy/" + tuiYear + "/" + semester,
						method: "POST",
						headers: {
							"Content-Type": "application/json"
						},
						data: JSON.stringify(userData),
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



function refreshParent() {
		window.opener.location.reload(); // 부모 창 새로고침
	}

	// 자식 창 닫힐 때 부모 창 새로고침
	window.onunload = function() {
		refreshParent();
	};



}