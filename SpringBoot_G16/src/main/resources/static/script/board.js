function idCheck(){
	if(document.frm.id.value==""){
		alert("아이디를 입력하세요");
		document.frm.id.focus();
		return;
	}
	var k = document.frm.id.value;
	var opt = "toolbar=no, menubar=no, resizable=no, width=450, height=200";
	window.open("idcheck?id="+k,"id check",opt);
}// 아이디 중복체크 팝업창

function idok(userid){
	opener.frm.id.value=userid;
	opener.frm.re_id.value=userid;
	self.close();
}// 아이디 중복체크 완료 동작

function open_win(url, name){
	window.open(url, name, "toolbar=no, menubar=no, scrollbars=no, "
			+ " resizable=no, width=500, height=230");
}// 게시물 수정 삭제시 비번 입력창