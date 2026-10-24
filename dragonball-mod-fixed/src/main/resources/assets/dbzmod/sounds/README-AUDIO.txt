COMO AGREGAR TU MUSICA Y EFECTOS DE SONIDO
=========================================

1. Convierte tus audios a formato .ogg (Minecraft solo lee .ogg, no mp3/wav).
   Con ffmpeg:   ffmpeg -i mi_sonido.mp3 -c:a libvorbis ki_blast_fire.ogg
   Sin instalar nada: usa https://convertio.co o cualquier conversor online.

2. Coloca los archivos con estos nombres EXACTOS en esta carpeta:

   ki_blast_fire.ogg       -> sonido al disparar el ki
   ki_blast_hit.ogg        -> sonido cuando el ki impacta
   kaioken_transform.ogg   -> grito/musica al activar el Kaio-ken

3. OJO CON LOS DERECHOS DE AUTOR: la musica original de Dragon Ball
   es propiedad de Toei/Shueisha. Para publicar tu mod usa musica
   libre o hecha por ti. Para uso personal, tu te haces responsable.

4. Mientras no agregues los .ogg, el juego usara silencio en esos eventos
   (no crasheara, solo aparecera un aviso en la consola).
