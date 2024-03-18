	console.log("tuitionBill 페이지 진입 성공");
	function printPage() {
		window.print();
	}

	let tuiAmount = document.getElementById('tuiAmount').value;
	console.log(tuiAmount);
	let schAmount = document.getElementById('schAmount').value;
	console.log(schAmount);
	let totalAmount = new Intl.NumberFormat().format(tuiAmount - schAmount);
	console.log(totalAmount)
	document.getElementById('totalAmount').textContent = totalAmount;


// IMP 객체 초기화
var IMP = window.IMP;
IMP.init("imp04766220");

// 결제 요청 함수
function requestPay() {
    IMP.request_pay({
        pg: "kakaopay",
        pay_method: "card",
        merchant_uid: "unique_merchant_uid", // 상점에서 생성한 고유 주문 번호
        name: "등록금 납부", // 상품명 또는 주문 내용
        amount: 10000, // 결제 금액 (원 단위)
        buyer_email: "buyer@example.com", // 구매자 이메일
        buyer_name: "구매자 이름", // 구매자 이름
        buyer_tel: "010-1234-5678", // 구매자 전화번호
        buyer_addr: "구매자 주소", // 구매자 주소
        buyer_postcode: "123-456", // 구매자 우편번호
        status: "paid",

    }, function (rsp) {
        // 결제 결과 처리
        if (rsp.success) {
            // 결제 성공 시 처리할 작업
            alert("결제가 완료되었습니다.");
            // 예를 들어, 결제 성공 후 화면 갱신이나 다음 단계로 이동하는 등의 작업을 수행할 수 있습니다.
        } else {
            // 결제 실패 시 처리할 작업
            var msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;
            alert(msg);
        }
    });
}