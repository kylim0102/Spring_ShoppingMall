window.onload = function() {
    updateSubCategory();
};

function updateSubCategory() {
    var selectCategory = document.getElementById("selectCategory");
    var subCategory = document.getElementById("subCategories");
    var subCategoryIdx = document.getElementById("sub_category_idx");
    var categoryId = selectCategory.value;

    if (!categoryId || categoryId == "") {
        subCategory.style.display = "none";
    } else {
        // 서브 카테고리가 없는 경우, 셀렉트 박스를 숨깁니다.
        subCategory.style.display = "none";
    }

    // 서버로부터 서브 카테고리 데이터를 받아오는 Ajax 요청
    fetch(`/api/subcategories?parentIdx=${categoryId}`)
        .then(response => response.json())
        .then(data => {
            subCategory.innerHTML = ""; // 기존 옵션 제거
            subCategoryIdx.value = "";

            // 서브 카테고리가 있는 경우에만 셀렉트 박스를 보여줍니다.
            if (data.length > 0) {
                subCategory.style.display = "block";

                data.forEach(function(subCategoryData) {
                    var optionElement = document.createElement("option");
                    optionElement.text = subCategoryData.name;
                    optionElement.value = subCategoryData.idx;
                    subCategory.appendChild(optionElement);
                });
            } else {
                subCategory.style.display = "none";
            }
        })
        .catch(error => console.error('Error:', error));
}