<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<!-- 상단 제목부분 -->
<div class="breadcrumbs" data-aos="fade-in">
    <div class="container">
        <h2>캠퍼스 맵</h2>
    </div>
</div>
<!-- 상단 제목부분 끝 -->

<!-- 지도와 캠퍼스 목록 -->
<div class="container-xxl py-5">
    <div class="container">
        <div class="row g-5">
            <!-- 지도 -->
            <div class="col-lg-6 wow fadeInUp" data-wow-delay="0.3s">
                <div class="col-lg-12 wow fadeInUp" data-wow-delay="0.1s" style="min-height: 400px;">
                    <div class="position-relative h-100">
                        <div id="map" style="width: 100%; height: 700px;"></div>
                    </div>
                </div>
            </div>
            <!-- 캠퍼스 목록 -->
            <div class="col-lg-6">
                <div class="card">
                <div class="card-body">
                    <h5 class="card-title mt-0 mb-3">서면캠퍼스</h5>
                    <ul class="nav nav-tabs" id="campus-tab" role="tablist">
                        <li class="nav-item" role="presentation">
                            <a class="nav-link active" id="3rdFloor-tab" data-bs-toggle="tab" href="#3rdFloor" role="tab" aria-controls="3rdFloor" aria-selected="true">3층</a>
                        </li>
                        <li class="nav-item" role="presentation">
                            <a class="nav-link" id="4thFloor-tab" data-bs-toggle="tab" href="#4thFloor" role="tab" aria-controls="4thFloor" aria-selected="false">4층</a>
                        </li>
                    </ul>
                    <div class="tab-content" id="campus-tabContent">
                        <!-- 3층 -->
<div class="tab-pane fade show active" id="3rdFloor" role="tabpanel" aria-labelledby="3rdFloor-tab">
    <div class="card-text">
        <h6>학과</h6>
        <ul class="list-group">
            <li class="list-group-item">컴퓨터공학과</li>
            <li class="list-group-item">전자공학과</li>
            <li class="list-group-item">화학공학과</li>
            <li class="list-group-item">기계공학과</li>
            <li class="list-group-item">신소재공학과</li>
            <li class="list-group-item">철학과</li>
            <li class="list-group-item">국사학과</li>
            <li class="list-group-item">언어학과</li>
            <li class="list-group-item">국어국문학과</li>
            <li class="list-group-item">영어영문학과</li>
        </ul>
    </div>
    <div class="card-text mt-4">
        <h6>강의실</h6>
        <ul class="list-group">
            <li class="list-group-item">3층 강의실 1</li>
            <li class="list-group-item">3층 강의실 2</li>
            <li class="list-group-item">3층 강의실 3</li>
            <li class="list-group-item">3층 강의실 4</li>
             <li class="list-group-item">3층 강의실 5</li>
              <li class="list-group-item">3층 강의실 6</li>
               <li class="list-group-item">3층 강의실 7</li>
        </ul>
    </div>
</div>
                        <!-- 4층 -->
                        <div class="tab-pane fade" id="4thFloor" role="tabpanel" aria-labelledby="4thFloor-tab">
                               <div class="card-text">
        <h6>학과</h6>
        <ul class="list-group">
            <li class="list-group-item">심리학과</li>
            <li class="list-group-item">정치외교학과</li>
            <li class="list-group-item">사회복지학과</li>
            <li class="list-group-item">언론정보학과</li>
            <li class="list-group-item">인류학과</li>
            <li class="list-group-item">경영학과</li>
            <li class="list-group-item">경제학과</li>
            <li class="list-group-item">회계학과</li>
            <li class="list-group-item">농업경영학과</li>
            <li class="list-group-item">무역학과</li>
        </ul>
    </div>
    <div class="card-text mt-4">
        <h6>강의실</h6>
        <ul class="list-group">
            <li class="list-group-item">4층 강의실 1</li>
            <li class="list-group-item">4층 강의실 2</li>
            <li class="list-group-item">4층 강의실 3</li>
            <li class="list-group-item">4층 강의실 4</li>
            <li class="list-group-item">4층 강의실 5</li>
            <li class="list-group-item">4층 강의실 6</li>
            
        </ul>
    </div>
                        </div>
                    </div>
                </div>
            </div>
            </div>
        </div>
    </div>
</div>
<!-- 지도와 캠퍼스 목록 끝 -->

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5275edbfc405960aad1f6f12211cdd04"></script>
<script type="text/javascript">
    var container = document.getElementById('map');
    var options = {
        center: new kakao.maps.LatLng(35.159593807346944, 129.060189192107),
        level: 3
    };

    var map = new kakao.maps.Map(container, options);

    var markerPosition = new kakao.maps.LatLng(35.159593807346944, 129.060189192107);
    var marker = new kakao.maps.Marker({
        position: markerPosition
    });
    marker.setMap(map);

    var iwContent = '<div style="padding:10px;"><img src="/img/logo.png" style="width: 70%; height: 70%;" /><br> <a href="https://map.kakao.com/link/to/그린사이버대학교,35.159593807346944, 129.060189192107" style="color:blue" target="_blank">길찾기</a></div>';
    var iwPosition = new kakao.maps.LatLng(35.159593807346944, 129.060189192107);
    var infowindow = new kakao.maps.InfoWindow({
        position: iwPosition,
        content: iwContent
    });
    infowindow.open(map, marker);
</script>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
