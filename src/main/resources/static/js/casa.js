$('#confirmacaoExclusaoModal').on('show.bs.modal',function(event){
	
	var button = $(event.relatedTarget);
	
	var codigoCasa = button.data('codigo');
	var descricaoCasa = button.data('nomeCasa');
	
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.attr('action');
	if(!action.endsWith('/')) {
		action += '/';
		
	}
	form.attr('action', action + codigoCasa);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir!!!');
	
});