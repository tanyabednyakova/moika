/**
 * Вспомогательный инстурументарий для большей
 * дружелюбности сайта (основан на framework Bootstrap)
 *
 * @since 2017-03-05
 * @author Kovalev Aleksandr
 */

/*Вспомогательные глобальные переменные*/


/**
 * Сделать input-элемент активным для ajax-проверки с сервера
 *
 * @param toURL - адрес куда отсылать проверяемое значение
 * @param selector - Селектор input элемента сожержимое которого надо проверять
 */
function setActiveFormInput(toURL, selector){
    var div = $(selector).closest('div.form-group');
    var glyphicon = div.find('span.glyphicon');
    $(selector).change(function(){
        var value = $(this).val();
        if(value){
            var obj = new Object();
            obj[$(selector).attr('name')] = value;
            $.ajax({
                method:"POST",
                url:toURL,
                data:JSON.stringify(obj),
                success:function(data){
                    var obj = $.parseJSON(data);
                    if(obj.success){
                        if(!div.hasClass('has-success')){
                            div.removeClass('has-error').addClass('has-success');
                            gliphicon.removeClass("glyphicon-remove").addClass("glyphicon-ok");
                            if($(selector).hasClass('popover-dismissible')){
                                $(selector).removeClass('popover-dismissible')
                            }
                        }
                    }else{
                        if(!div.hasClass('has-error')){
                            div.removeClass('has-success').addClass('has-error');
                            if(!$(selector).hasClass('popover-dismissible')){
                                $(selector).addClass('popover-dismissible');
                                gliphicon.removeClass("glyphicon-ok").addClass("glyphicon-remove");
                                $(selector).popover({
                                    content:'<span style="color:#A94442">please input correct text</span>',
                                    html:true,
                                    placement:'auto',
                                    trigger:'hover'
                                }).popover('show');
                            }
                        }
                    }
                }
            });
        }else{
            $(selector).popover({
                content:'<span style="color:#A94442">must not be empty</span>',
                html:true,
                placement:'auto',
                trigger:'hover'
            }).popover('show');
        }
    });
}

/**
 * Отправить элементы формы на сервер в виде json-объекта
 *
 * @param toURL - адрес куда отсылать данные
 * @param selector - Селектор формы
 * @param method - метод откправки данных ( POST|GET|PUT|DELETE... )
 */
function formSubminAjax(toURL, selector, method){
    var obj = parseFormToJSON(selector);
    $.ajax({
        method :(method==undefined?"POST":method),
        url : toURL,
        data : obj
    });
}

/**
 * Преобразовать элементы формы в json-объекта
 *
 * @param selector - Селектор формы
 */
function parseFormToJSON(selector){
    var obj = new Object();
    $(selector).find('[name]').each(function(){
        obj[$(this).attr('name')]=$(this).val();
    });
    return  JSON.stringify(obj)
}

