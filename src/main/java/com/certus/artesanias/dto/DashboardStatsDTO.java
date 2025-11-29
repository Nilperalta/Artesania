package com.certus.artesanias.dto;

public class DashboardStatsDTO {
    
    private Long totalUsuarios;
    private Long totalVendedores;
    private Long totalCompradores;
    private Long totalAdmins;
    private Long usuariosActivos;
    private Long usuariosInactivos;
    private Long alertasActivas;
    private Double tasaCrecimiento;
    
    public DashboardStatsDTO() {}
    
    public DashboardStatsDTO(Long totalUsuarios, Long totalVendedores, Long totalCompradores, 
                            Long totalAdmins, Long usuariosActivos, Long usuariosInactivos, 
                            Long alertasActivas, Double tasaCrecimiento) {
        this.totalUsuarios = totalUsuarios;
        this.totalVendedores = totalVendedores;
        this.totalCompradores = totalCompradores;
        this.totalAdmins = totalAdmins;
        this.usuariosActivos = usuariosActivos;
        this.usuariosInactivos = usuariosInactivos;
        this.alertasActivas = alertasActivas;
        this.tasaCrecimiento = tasaCrecimiento;
    }
    
    public Long getTotalUsuarios() { return totalUsuarios; }
    public void setTotalUsuarios(Long totalUsuarios) { this.totalUsuarios = totalUsuarios; }
    
    public Long getTotalVendedores() { return totalVendedores; }
    public void setTotalVendedores(Long totalVendedores) { this.totalVendedores = totalVendedores; }
    
    public Long getTotalCompradores() { return totalCompradores; }
    public void setTotalCompradores(Long totalCompradores) { this.totalCompradores = totalCompradores; }
    
    public Long getTotalAdmins() { return totalAdmins; }
    public void setTotalAdmins(Long totalAdmins) { this.totalAdmins = totalAdmins; }
    
    public Long getUsuariosActivos() { return usuariosActivos; }
    public void setUsuariosActivos(Long usuariosActivos) { this.usuariosActivos = usuariosActivos; }
    
    public Long getUsuariosInactivos() { return usuariosInactivos; }
    public void setUsuariosInactivos(Long usuariosInactivos) { this.usuariosInactivos = usuariosInactivos; }
    
    public Long getAlertasActivas() { return alertasActivas; }
    public void setAlertasActivas(Long alertasActivas) { this.alertasActivas = alertasActivas; }
    
    public Double getTasaCrecimiento() { return tasaCrecimiento; }
    public void setTasaCrecimiento(Double tasaCrecimiento) { this.tasaCrecimiento = tasaCrecimiento; }
}