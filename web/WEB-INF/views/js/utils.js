/**
 * Вспомогательный инстурументарий для большей
 * дружелюбности сайта (основан на framework Bootstrap)
 *
 * @since 2017-03-05
 * @author Kovalev Aleksandr
 */

/**
 * Сделать input-элемент активным для ajax-проверки с сервера
 *
 * @param toURL - адрес куда отсылать проверяемое значение
 * @param selector - Селектор input элемента сожержимое которого надо проверять
 * @param result - callback функция вызывается при приеме запроса в нее передается булевый параметр:
 * true если ответс с сервера положительный
 * false если отрицательный
 */
function setActiveFormInput(toURL, selector, result){
    var div = $(selector).closest('div.form-group');
    var glyphicon = div.find('span.glyphicon');
    setChangeListener(selector, 1000, function(){
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
                        if(result){
                            result(true);
                        }
                        if(!div.hasClass('has-success')){
                            div.removeClass('has-error').addClass('has-success');
                            gliphicon.removeClass("glyphicon-remove").addClass("glyphicon-ok");
                            if($(selector).hasClass('popover-dismissible')){
                                $(selector).removeClass('popover-dismissible')
                            }
                        }
                    }else{
                        if(result){
                            result(false);
                        }
                        if(!div.hasClass('has-error')){
                            div.removeClass('has-success').addClass('has-error');
                            if(!$(selector).hasClass('popover-dismissible')){
                                $(selector).addClass('popover-dismissible');
                                gliphicon.removeClass("glyphicon-ok").addClass("glyphicon-remove");
                                $(selector).popover({
                                    //TODO Возможно стоит вставлять сообение с сервера?!
                                    content:'<span style="color:#a94442">Введенные Вами данные уже кем-то используюься, введите другое значение</span>',
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
                content:'<span style="color:#a94442">Введите данные</span>',
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

/**
* Обработать ошибки полученные с бэкэнда
*
* @param errors - Ошибки
*/
function processErrors(errors){
    if(errors){
        $.each(errors,function (name ,error) {
            var selector = '[name="'+name+'"]';
            setStatusElement(selector,'error',error);
        });
    }
}

/**
 * Сбросить статус (оформление) элемента на стандартный
 *
 * @param selector - Селектор input-элемента
 */
function resetStatusElement(selector) {
    var elem = $(selector);
    var div = elem.closest('div.form-group');
    var gliphicon =  div.find('span.glyphicon');
    if(div.hasClass('has-error')){
        div.removeClass('has-error');
        gliphicon.removeClass("glyphicon-remove");
    }else if(div.hasClass('has-success')){
        div.removeClass('has-success');
        gliphicon.removeClass("glyphicon-ok");
    }else if(div.hasClass('has-warning')){
        div.removeClass('has-warning');
        gliphicon.removeClass("glyphicon-warning-sign");
    }
    if(!gliphicon.hasClass('hide')){
        gliphicon.addClass('hide');
    }
    if(elem.hasClass('popover-dismissible')){
        elem.removeClass('popover-dismissible')
    }
}

/**
 * Устанавить статус (оформление) элемента на стандартный
 *
 * @param selector - Селектор input-элемента
 * @param status - статус элемента (его оформление), строковое значение, может принимать одно из трех значений:
 * error | warning | success
 * @param msg - текст всплывающий подсказки
 *
 */
function setStatusElement(selector,status,msg) {
    var elem = $(selector);
    if(elem&&status&&status.search(/^(error|warning|success)$/i)>-1){
        resetStatusElement(selector);
        var div = elem.closest('div.form-group');
        var gliphicon =  div.find('span.glyphicon');
        var color = 'black';
        switch (status){
            case 'error':
                div.addClass('has-error');
                gliphicon.addClass("glyphicon-remove");
                color='#a94442';
                break;
            case 'warning':
                div.addClass('has-warning');
                gliphicon.addClass("glyphicon-warning-sign");
                color = '#8a6d3b';
                break;
            case 'success':
                div.addClass('has-success');
                gliphicon.addClass("glyphicon-ok");
                color = '#3c763d';
                break;
        }
        if(gliphicon.hasClass('hide')){
            gliphicon.removeClass('hide');
        }
        if(msg){
            elem.addClass('popover-dismissible');
            elem.popover({
                content:'<span style="color:'+color+'">'+msg+'</span>',
                html:true,
                placement:'auto',
                trigger:'hover'
            }).popover('show');
        }
    }
}

/**
 * Устанавить событие на изменение элемента
 *
 * @param selector - Селектор элемента
 * @param ms - задержка в миллисекундах
 * @param action - callback функция которая запускается при срабатывании события
 * */
function setChangeListener(selector,ms,action) {
    if($(selector)&&ms&&ms>0){
        $(selector).on('input',function(){
            if($(this).attr('timer')){
                clearTimeout($(this).attr('timer'));
            }
            $(this).attr('timer',setTimeout(function(){
                action($(selector));
            },ms));
        });
    }
}

