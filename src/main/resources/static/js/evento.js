$('#confirmacaoExclusaoEvento').on('show.bs.modal', function(event){
	
	var button = $(event.relatedTarget);
	
	var codigoEvento = button.data('codigo');
	var descricaoEvento = button.data('descricao');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if(!action.endsWith('/')){
		action += '/';
	}
	form.attr('action', action + codigoEvento);
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o evento <strong>' + descricaoEvento +'</strong>?');
	
});
	
