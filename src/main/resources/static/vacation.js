console.log("vacation.js exe")

const vFindAll = async () => {
    try{
        const response = await axios.get("/dashboard/vacation");
        const data = response.data;

        const tbody = document.querySelector("#right_bottom_inner");
        let html = "";

        for (let i = 0; i < data.length; i++) {
            const vacation = data[i];
            html += `
                <div class="box">
                    <div class="line1">
                        <div>${vacation.sname}</div>
                        <button class="vacationDel" onclick="vDelete(${vacation.vcode})">신청취소</button>
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
    }catch (e) {
        console.error(e);
    }
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
    let obj = { scode, vstart, vend, vreason };
    try {
        const response = await axios.post(`/dashboard/vacation`, obj);
        const data = response.data;
        if (data === true) {
            alert("등록성공");
            scodeInput.value = '';            vstartInput.value = '';
            vendInput.value = '';            vreasonInput.value = '';
            vFindAll();
        } else {
            alert("등록실패");
        }
    } catch (e) {console.error(e); }
}

const vDelete = async ( vcode ) =>{

    const response = await axios.delete(`/dashboard/vacation?vcode=${vcode}`);
    const data = response.data;
    if(data == true){
        alert("삭제성공")
        vFindAll();
    }else{alert("삭제실패")}
}