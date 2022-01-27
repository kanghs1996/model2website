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

	if (form.password.value == "") {
		document.getElementById('modal-p').innerHTML = '비밀번호를 입력하세요';
		flag = form.password;
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