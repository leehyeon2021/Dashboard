console.log("department.js exe")

const dFindAll = async () => {
    const tbody = document.querySelector("#departmentTable tbody");
    let html = "";
    const response = await axios.get("/dashboard/department");

    const data = response.data;
    for (let i = 0; i <= data.length-1; i++){
        const department = data[i];
        html += `<tr>
                    <td>
                        <div class="dpt">
                                    <span>${department.dname}</span>
                                    <div class="dptbtn">
                                        <button class="update" onclick="dUpdate(${department.dcode})">수정</button>
                                        <button class="delete" onclick="dDelete(${department.dcode})">삭제</button>
                                    </div>
                        </div>
                    </td>
                </tr>
                `

    }
    tbody.innerHTML = html;
}
dFindAll();

const dPost = async () =>{
    const dnameInput = document.querySelector('.dname');
    const dname = dnameInput.value;
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

const dUpdate = async ( dcode ) => {
    const dname = prompt("수정할 부서명")
    const obj = {"dname" : dname, "dcode" : dcode}
    const response = await axios.put(`/dashboard/department`, obj);
    const data = response.data;
    if (data == true) {
        alert("수정성공")
        dFindAll();
    } else {
        alert("수정실패")
    }
}

const dDelete = async ( dcode ) =>{

    const response = await axios.delete(`/dashboard/department?dcode=${dcode}`);
    const data = response.data;
    if(data == true){
        alert("삭제성공")
        dFindAll();
    }else{alert("삭제실패")}
}