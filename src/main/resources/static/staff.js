console.log("staff.js open");

// 2. 사원 전체 목록
const sFindAll = async () =>{
    const tbody = document.querySelector(".staffTable tbody");
    let html ="";
        const response = await axios.get("/dashboard/staff")
        const data = response.data;
        for( let i=0; i<=data.length-1;i++){
            const staff = data[i];
        html = `<tr>
                    <td>${staff.sname}</td>
                    <td>${staff.dcode}</td>
                    <td>${staff.srank}</td>
                    <td>
                        <button onclick="sUpdate()" class="update">수정</button>
                        <button onclick="sDelete()" class="delete">삭제</button>
                    </td>
                </tr>`;
        };
        tbody.innerHTML = html;
}
sFindAll();

// 1. 사원등록
const sPost = async () => {
    const sname = document.querySelector("#staffName").value;
    const srank = document.querySelector("#staffLank").value;
    const obj = { sname , srank };
    const response = await axios.post( "/dashboard/staff" , obj);
    const data = response.data;
    if(data){
        alert("등록 성공");
        sname=''; srank='';
        sFindAll();
    }else{alert("등록 실패")}    
}

// 3. 사원 수정
const sUpdate = async ( sno ) => {
    const sname = prompt("수정할 사원명을 입력하세요.");
    const srank = prompt("수정할 직급명을 입력하세요.");
    const obj = { sname , srank };
    const response = await axios.put("/dashboard/staff", obj);
    const data = response.data;
    if(data== true){
        alert("수정이 완료되었습니다.")
        sFindAll();
    }else{alert("수정에 실패하였습니다.")}
}

// 4. 사원 삭제
const sDelete = async ( sno ) => {

}