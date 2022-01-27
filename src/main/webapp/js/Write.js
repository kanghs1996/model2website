var myModal = new bootstrap.Modal(document.getElementById('myModal'), {
	keyboard: false
});
var flag;
var modal_b = document.getElementsByName('modal-b');
form.addEventListener("submit", function(e) {
	if (form.title.value == "") {
		document.getElementById('modal-p').innerHTML = '제목을 입력하세요';
		flag = form.title;
		myModal.show();
		e.preventDefault();
		return;
	}

	if (form.content.value == "") {
		document.getElementById('modal-p').innerHTML = '내용을 입력하세요';
		flag = form.content;
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