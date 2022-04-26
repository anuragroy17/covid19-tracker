$(document).ready(function () {
  loadConfirmedData();
});

function loadConfirmedData() {
  $.get('getConfirmedData').done(function (fragment) {
    $('#content').replaceWith(fragment);
    $('#confirmedDataTable').DataTable({
      aoColumnDefs: [
        {
          bSortable: true,
          aTargets: [-1] /* 1st one, start by the right */,
        },
      ],
    });
  });
}

function loadDeathData() {
  $.get('getDeathData').done(function (fragment) {
    $('#content').replaceWith(fragment);
    $('#deathDataTable').DataTable({
      aoColumnDefs: [
        {
          bSortable: true,
          aTargets: [-1] /* 1st one, start by the right */,
        },
      ],
    });
  });
}

function loadRecoveredData() {
  $.get('getRecoveredData').done(function (fragment) {
    $('#content').replaceWith(fragment);
    $('#recoveredDataTable').DataTable({
      aoColumnDefs: [
        {
          bSortable: true,
          aTargets: [-1] /* 1st one, start by the right */,
        },
      ],
    });
  });
}

$('button.btn').click(function () {
  $('button.btn').removeClass('active');
  $(this).addClass('active');
});
