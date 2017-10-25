$('#new-note-textarea').keyup(function() {
	$('#charcounter').text((500 - $(this).val().length) + ' characters left.');
	console.log($(this).val().length);
});

$(document).ready(function() {
	var url = window.location;
	$('ul.nav a').filter(function() {
		return this.href == url || (this.href + '#') == url;
	}).parent().addClass('active');
});

$('#randomize-btn').click(function() {
	$.ajax({
		cache : false,
		url : '/randomnote',
		success : function(result) {
			$('#note-of-moment-text').text(result.text);
		}
	});
});

$('#copy-text-btn').click(function () {
	var $temp = $('<input>');
	$('body').append($temp);
	$temp.val($('#note-of-moment-text').text()).select();
	document.execCommand('copy');
	$temp.remove();
});

$('#notetextheader').click(function(){
	if ($('#new-note-form').is(":visible")) {
		//hide
		$('#new-note-form').slideUp();
		$('#notetextheader').text('Show new note form');
	} else {
		//show
		$('#new-note-form').slideDown();
		$('#notetextheader').text('Write a note (or click to hide)');
	}
});