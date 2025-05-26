window.onload = () => {
    fetchArticles();
    fetchMeal();
    fetchTimetable();
    loadTimetableGrid();
};

function fetchArticles() {
    const sortType = document.getElementById("sort-select").value;
    let url = "/api/articles";

    if (sortType === "likes") {
        url += "?sort=likes";
    } else {
        url += "?sort=latest";
    }

    fetch(url)
        .then(res => res.json())
        .then(articles => {
            const list = document.getElementById("post-list");
            list.innerHTML = "";

            articles.forEach(article => {
                const item = document.createElement("li");
                item.innerHTML = `
                    <strong>${article.title}</strong><br>
                    <small>작성자: ${article.author}</small> |
                    <small>좋아요: ${article.likeCount || 0}</small>
                `;
                list.appendChild(item);
            });
        })
        .catch(err => {
            console.error("게시글 불러오기 실패:", err);
            alert("게시글을 불러오는 중 오류가 발생했습니다.");
        });
}

function fetchMeal() {
    const today = new Date().toISOString().slice(0, 10).replace(/-/g, "");
    fetch(`/api/meal?date=${today}`)
        .then(res => res.json())
        .then(meals => {
            const mealBox = document.createElement("div");
            mealBox.classList.add("section");
            mealBox.innerHTML = `
                <h2>오늘의 급식</h2>
                <p>${meals.length ? meals[0].menu.replace(/<br\/>/g, "<br>") : "급식 정보 없음"}</p>
            `;
            document.querySelector(".main-content").appendChild(mealBox);
        });
}

function fetchTimetable() {
    // 유지됨 (단일 학년/반 시간표 불러오기 용도면 제거 가능)
}

function loadTimetableGrid() {
    const grade = document.getElementById("grade-select").value;
    const date = new Date().toISOString().slice(0, 10).replace(/-/g, "");
    const classRange = [1, 2, 3, 4, 5, 6, 7, 8, 9];

    const requests = classRange.map(cls =>
        fetch(`/api/timetable?grade=${grade}&classNM=${cls}&date=${date}`)
            .then(res => res.json())
            .then(periods => ({ classNM: cls, periods }))
    );

    Promise.all(requests).then(classTables => {
        const maxPeriod = Math.max(...classTables.map(ct => ct.periods.length), 7);

        let tableHtml = `<table border="1" style="width:100%; text-align:center">
            <thead><tr><th>교시</th>${classRange.map(cls => `<th>${cls}반</th>`).join("")}</tr></thead>
            <tbody>`;

        for (let p = 1; p <= maxPeriod; p++) {
            tableHtml += `<tr><td>${p}</td>`;
            classTables.forEach(ct => {
                const cell = ct.periods.find(per => per.period === String(p));
                tableHtml += `<td>${cell ? cell.subject : "-"}</td>`;
            });
            tableHtml += `</tr>`;
        }

        tableHtml += `</tbody></table>`;

        const container = document.getElementById("timetable-container");
        container.innerHTML = tableHtml;
    });
}
