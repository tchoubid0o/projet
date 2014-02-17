<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="WEB-INF/pages/header.jsp" />

<div id="content">
	<section class="width1000" style="margin-top: 35px;">
		<jsp:include page="WEB-INF/pages/sidebar.jsp" />
		<div id="right">
			<div class="right_content">
				<section id="contentGrandeImage" style="width: auto;">
					<h2	class="h2_right_content">Erreur 404	Fichier non trouvé !</h2>
					<section id="commentGrandeImage" style="width: auto;font-family: Calibri, Arial, sans-serif;">
						La page que vous avez demandé: a été retirée, a changé d'adresse,
						n'existe pas ou est temporairement indisponible.<br />
						<br /> Assurez-vous que l'adresse que vous avez tapée est correcte,
						Si c'est le cas, nous vous conseillons de retourner sur la page
						d'accueil.<br />
						<br /> Vous devriez prévenir le responsable du site d'où vous venez
						pour qu'il modifie ses liens<br/><br/>
						<script style="text-align:center;" type="text/javascript">
							a = '2587.swf';
							document.write('<div><object style="width: 100%; height: 450px;"><param name="autostart" value="true" ><param name="src" value="' + a + '"><embed  src="' + a + '" autostart="true"><\/object><\/div>');
						</script>
					</section>
				</section>
				<div style="clear: both"></div>
			</div>
		</div>
		<div style="clear: both"></div>
	</section>
</div>

<!-- Footer -->
<jsp:include page="WEB-INF/pages/footer.jsp" />