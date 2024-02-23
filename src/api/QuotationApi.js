import axios from "axios";

const searchItem = async (data) => {
    try{
        const response = await axios.post("http://localhost:8080/search",data)
        return response.data;
    } catch (e) {
        console.log("데이터를 불러오지 못했습니다.");
    }
}

const addQuotation = async (data) => {
    try{
        const response = await axios.post(`http://localhost:8080/quotation/${data.companyName}`, data.itemIdList);
        return response.data;
    } catch (e) {
        console.log("데이터를 저장하는데 실패하였습니다.");
    }
}

const getQuotationList = async (data) => {
    try{
        const {companyName, page} = data;
        const response = await axios.get(`http://localhost:8080/quotations/${companyName}?page=${page}`);
        return response.data;
    } catch (e) {
        console.log("데이터를 불러오는데 실패했습니다.");
    }
}

const searchQuotation = async (data) => {
    try {
        const {searchData} = data;
        const response = await axios.post('http://localhost:8080/quotations/search', searchData);
        return response.data;
    } catch (e) {
        console.log("데이터 찾기를 실패하였습니댜.");
    }
}

export {searchItem, addQuotation, getQuotationList,searchQuotation};
