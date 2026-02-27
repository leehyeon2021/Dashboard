console.log("vacation.js exe")

const vFindAll = async () => {
    const tbody = document.querySelector("#right_bottom_inner");
    let html = "";
    const response = await axios.get("/dashboard/vacation");

    const data = response.data;
    for (let i = 0; i <= data.length-1; i++){
        const vacation = data[i];
        let staffName = ""; // 사원명을 가져오기위한 변수
        for(let j = 0; j < staff.length; j++){ // 사원 배열을 순회
            if(vacation.scode == staff[j].scode){ // 일치하는 사원코드을 찾으면
                staffName = staff[j].staffName; //사원명 저장
                break;
            }
        }
        html += `
                <div class="box">
                    <div class="line1">
                        <div>${staffName}</div><button class="vacationDel" onclick="vDelete(${vacation.vcode})">신청취소</button>
                    </div>
                    <div class="line2">
                        ${vacation.vstart}~${vacation.vend}
                    </div>
                    <div class="line3">
                        ${vacation.vreason}
                    </div>
                </div>
        `;

    }
    tbody.innerHTML = html;
}
vFindAll();

const vPost = async () =>{
    const scodeInput = document.querySelector('.staffselect');
    const scode = scodeInput.value;
    const vstartInput = document.querySelector('.startdate');
    const vstart = vstartInput.value;
    const vendInput = document.querySelector('.enddate');
    const vend = vendInput.value;
    const vreasonInput = document.querySelector('.reason');
    const vreason = vreasonInput.value;
    try {
        const response = await axios.post(`/dashboard/department?dname=${dname}`);
        const data = response.data;

        if (data === true) {
            alert("등록성공");
            dnameInput.value = '';
            dFindAll();
        } else {
            alert("등록실패");
        }
    } catch (e) {
        console.error(e);
    }
}

const vDelete = async ( dcode ) =>{

    const response = await axios.delete(`/dashboard/department?dcode=${dcode}`);
    const data = response.data;
    if(data == true){
        alert("삭제성공")
        dFindAll();
    }else{alert("삭제실패")}
}