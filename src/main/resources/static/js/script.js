$(document).ready(function () {
  $.get('getTotalConfirmedCases')
    .done(function (fragment) {
      $('#stats').replaceWith(fragment);
    })
    .then(
      $.get('getConfirmedLocationStats').done(function (fragment) {
        $('#table_content').replaceWith(fragment);
        $('#confirmedCaseTable').DataTable({
          aoColumnDefs: [
            {
              bSortable: true,
              aTargets: [-1] /* 1st one, start by the right */,
            },
          ],
        });
      })
    );
});

function loadConfirmedCasesData() {
  $.get('getTotalConfirmedCases')
    .done(function (fragment) {
      $('#stats').replaceWith(fragment);
    })
    .then(
      $.get('getConfirmedLocationStats').done(function (fragment) {
        $('#table_content').replaceWith(fragment);
        $('#confirmedCaseTable').DataTable({
          aoColumnDefs: [
            {
              bSortable: true,
              aTargets: [-1] /* 1st one, start by the right */,
            },
          ],
        });
      })
    );
}
