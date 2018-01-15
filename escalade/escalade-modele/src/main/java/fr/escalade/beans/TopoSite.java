package fr.escalade.beans;

import java.io.Serializable;

public class TopoSite implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id_topo_site;
	private Topo topo;
	private Site site;

	public TopoSite() {
	}

	public TopoSite(Long id_topo_site, Topo topo, Site site) {
		super();
		this.id_topo_site = id_topo_site;
		this.topo = topo;
		this.site = site;
	}

	public Long getId_topo_site() {
		return id_topo_site;
	}

	public void setId_topo_site(Long id_topo_site) {
		this.id_topo_site = id_topo_site;
	}

	public Topo getTopo() {
		return topo;
	}

	public void setTopo(Topo topo) {
		this.topo = topo;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

}
