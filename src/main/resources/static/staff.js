console.log("staff.js open");

// 2. 사원 전체 목록
const sFindAll = async () =>{
    const tbody = document.querySelector(".staffTable tbody");
    const staffselect = document.querySelector(".staffselect");
    let html =``;
    let shtml = `<option value="disabled">휴가 신청 사원을 선택하세요.</option>`;
    try {
        const response = await axios.get("/dashboard/staff");
        const data = response.data;
        for (let i = 0; i <= data.length - 1; i++) {
            const staff = data[i];
            html += `<tr>
                    <td>${staff.sname}</td>
                    <td>${staff.dname}</td>
                    <td>${staff.srank}</td>
                    <td>
                        <button onclick="sUpdate(${staff.scode})" class="update">수정</button>
                        <button onclick="sDelete(${staff.scode})" class="delete">삭제</button>
                    </td>
                </tr>`;
            shtml += `<option value="${staff.scode}">${staff.sname}</option>`;
        }
        tbody.innerHTML = html;
        staffselect.innerHTML = shtml;
    }catch (e){console.log(e);}
}
sFindAll();

// 1. 사원등록
const sPost = async () => {
    const snameInput = document.querySelector(".staffName");
    const sname = snameInput.value;
    const srankInput = document.querySelector(".staffLank");
    const srank = srankInput.value;
    const dcodeInput = document.querySelector("#department");
    const dcode = dcodeInput.value;
    const obj = { sname , srank , dcode };
    const response = await axios.post( "/dashboard/staff" , obj);
    const data = response.data;
    if(data == true ){
        alert("등록 성공");
        snameInput.value=''; srankInput.value=''; dcodeInput.value='';
        sFindAll();
    }else{alert("등록 실패")}  
}

// 3. 사원 수정
const sUpdate = async ( scode ) => {
    const sname = prompt("수정할 사원명을 입력하세요.");
    const srank = prompt("수정할 직급명을 입력하세요.");
    const obj = { scode, sname , srank };
    const response = await axios.put("/dashboard/staff", obj);
    const data = response.data;
    if(data== true){
        alert("수정이 완료되었습니다.")
        sFindAll();
    }else{alert("수정에 실패하였습니다.")}
}

// 4. 사원 삭제
const sDelete = async ( scode ) => {
    const response = await axios.delete(`/dashboard/staff?scode=${ scode }`);
    const data = response.data;
    if( data == true ){
        alert("삭제 성공");
        sFindAll();
    }else{alert("삭제 실패")}
}