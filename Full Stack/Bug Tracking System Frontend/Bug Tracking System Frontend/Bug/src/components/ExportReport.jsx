const ExportReport = ({ filters }) => {
  const openReport = (url) => {
    const queryParams = new URLSearchParams(filters).toString();
    window.open(`http://localhost:8082/api/reports/${url}?${queryParams}`, "_blank"); // ✅ Opens file in new tab
  };

  return (
    <div style={{ display: "flex", justifyContent: "center", gap: "5px" }}>
      <button 
        onClick={() => openReport("export/pdf")} 
        className="btn btn- btn-primary lg active small-button"style={{ fontSize: "11px"}}
      >
        Generate Report 
      </button>

      <button 
        onClick={() => openReport("export/csv")} 
        className="btn btn-primary btn-lg active small-button" style={{ fontSize: "11px"}}
      >
        Generate CSV 
      </button>
    </div>
  );
};

export default ExportReport;
