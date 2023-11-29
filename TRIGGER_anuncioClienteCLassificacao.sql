CREATE OR REPLACE FUNCTION atualizar_media_classificacao()
RETURNS TRIGGER AS $$
DECLARE
    media_rating NUMERIC;
BEGIN
    SELECT AVG(classificacao) INTO media_rating
    FROM anuncio_cliente_classificacao
    WHERE id_anuncio = NEW.id_anuncio;
	AND anuncio_cliente_classificacao.classificacao <> 0
	
    UPDATE anuncio
    SET classificacao = media_rating
    WHERE id_anuncio = NEW.id_anuncio;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_atualizar_media_classificacao
AFTER INSERT OR UPDATE ON anuncio_cliente_classificacao
FOR EACH ROW
EXECUTE FUNCTION atualizar_media_classificacao();