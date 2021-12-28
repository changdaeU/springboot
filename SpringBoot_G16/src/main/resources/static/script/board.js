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



function boardCheck(){
	if(doucment.frm.pass.value==''){
		document.getElementById('message').innerHTML = '비밀번호는 게시물 수정 삭제시 필요합니다.';
		return false;
	}
	if(doucment.frm.title.value==''){
		document.getElementById('message').innerHTML = '제목은 필수입니다.';
		return false;
	}
	if(doucment.frm.content.value==''){
		document.getElementById('message').innerHTML = '게시물 내용을 비워둘 수 없습니다.';
		return false;
	}
	return true;
}

function reply_check(){
	if(document.frm2.reply.value.length==0){
		alert("댓글을 입력해 주세요");
		frm2.reply.focus();
		return false;
	}else{
		return true;
	}
}

function open_win(url, name){
	window.open(url, name, "toolbar=no, menubar=no, scrollbars=no, "
			+ " resizable=no, width=500, height=230");
}// 게시물 수정 삭제시 비번 입력창