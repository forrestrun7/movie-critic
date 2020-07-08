var stringRes = "";
// item selection
$('li').click(function () {
    $(this).toggleClass('selected');
    if ($('li.selected').length == 0)
        $('.select').removeClass('selected');
    else
        $('.select').addClass('selected');
    console.log($('li.selected').length);
    string();
});

// all item selection
$('.select').click(function () {
    if ($('li.selected').length == 0) {
        $('li').addClass('selected');
        $('.select').addClass('selected');
    }
    else {
        $('li').removeClass('selected');
        $('.select').removeClass('selected');
    }
    counter();
});

// number of selected items
function string() {
    if ($('li.selected').length > 0)
        $('.send').addClass('selected');
    else
        $('.send').removeClass('selected');
    $('.send').attr('data-counter',$('li.selected').length);
}
