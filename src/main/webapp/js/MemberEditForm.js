var myModal = new bootstrap.Modal(document.getElementById('myModal'), {
	keyboard: false
});
var flag;
var modal_b = document.getElementsByName('modal-b');
form.addEventListener("submit", function(e) {
	if (form.id.value == "") {
		document.getElementById('modal-p').innerHTML = '아이디를 입력하세요';
		flag = form.id;
		myModal.show();
		e.preventDefault();
		return;	
	}
	
	if (form.name.value == "") {
		document.getElementById('modal-p').innerHTML = '이름을 입력하세요';
		flag = form.name;
		myModal.show();
		e.preventDefault();
		return;
	}
	if (form.nick.value == "") {
		document.getElementById('modal-p').innerHTML = '닉네임을 입력하세요';
		flag = form.nick;
		myModal.show();
		e.preventDefault();
		return;
	}

	for (var i = 0; i < form.gender.length; i++) {
		if (form.gender[i].checked) {
			var isGenderChecked = '';
		}
	}
	if (isGenderChecked === undefined) {
		document.getElementById('modal-p').innerHTML = '성별을 선택하세요';
		flag = form.gender[0];
		myModal.show();
		e.preventDefault();
		return;
	}

	for (i = 0; i < form.interest.length; i++) {
		if (form.interest[i].checked) {
			var isInterestChecked = '';
		}
	}
	if (isInterestChecked === undefined) {
		document.getElementById('modal-p').innerHTML = '관심사항을 선택하세요';
		flag = form.interest[0];
		myModal.show();
		e.preventDefault();
		return;
	}

	if (form.education.value == '학력을 선택하세요') {
		document.getElementById('modal-p').innerHTML = '학력을 선택하세요';
		flag = form.education;
		myModal.show();
		e.preventDefault();
		return;
	}

	if (form.introduction.value == '') {
		document.getElementById('modal-p').innerHTML = '자기소개룰 입력하세요';
		flag = form.introduction;
		myModal.show();
		e.preventDefault();
		return;
	}
	
	if(!(form.pass.value == form.check.value)){
		document.getElementById('modal-p').innerHTML = '비밀번호가 일치하지 않아요';
		flag = form.check;
		myModal.show();
		e.preventDefault();
		return;
	}
});

for (var i = 0; i < modal_b.length; i++) {
	modal_b[i].addEventListener('click', function() {
		flag.focus();
	});
}