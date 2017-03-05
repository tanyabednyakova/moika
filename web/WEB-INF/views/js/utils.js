/**
 * Вспомогательный инстурументарий для большей
 * дружелюбности сайта
 *
 * @since 2017-03-02
 * @author Kovalev Aleksandr
 */


/**
 * Сделать input-элемент активным для ajax-проверки с сервера
 *
 * @param toURL - адрес куда отсылать проверяемое значение
 * @param selector - Селектор input элемента сожержимое которого надо проверять
 */
function setActiveFormInput(toURL, selector){
    var div = $(selector).closest('div.form-group');
    var gliphicon = div.find("spam.glyphicon");
    $(selector).change(function(){
        var value = $(this).val();
        $.ajax({
            method:"POST",
            url:toURL+value,
            success:function(data){
                var obj = $.parseJSON(data);
                if(obj.success){
                    //TODO
                }else{
                    //TODO
                }
            }
        });
    });
}