const callApi = async () => {
    try {
        const response = await axios.get(`/api/file`, {
            responseType: 'blob'
        });

        const contentDisposition = response.headers["content-disposition"];
        const match = contentDisposition.match(/filename\s*=\s*"(.+)"/i);
        const filename = match[1];

        fileDownload(response.data, filename);
    } catch (e) {
        console.error(e);
    }
}