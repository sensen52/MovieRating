    document.getElementById("orderBySelect").addEventListener("change", function() {
      // 선택된 option의 value 값을 가져옵니다.
      var selectedValue = this.value;

      // 선택된 value를 반영하여 페이지를 새로고침합니다.
      location.href = '/main?page=1&orderBy=' + selectedValue;
    });