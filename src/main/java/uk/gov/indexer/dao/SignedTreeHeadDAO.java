package uk.gov.indexer.dao;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import uk.gov.indexer.ctserver.SignedTreeHead;

interface SignedTreeHeadDAO extends DBConnectionDAO {

    String STH_TABLE = "sth";

    @SqlUpdate("create table if not exists " + STH_TABLE + " (tree_size integer, timestamp bigint, tree_head_signature varchar, sha256_root_hash varchar);" +
            "insert into " + STH_TABLE + "(tree_size, timestamp, tree_head_signature, sha256_root_hash) select (select count from total_entries), 1447421303202,'JATHxRF5gczvNPP1S1WuhD8jSx2bl+WoTt8bIE3YKvU=','BAMARzBFAiEAkKM3aRUBKhShdCyrGLdd8lYBV52FLrwqjHa5/YuzK7ECIFTlRmNuKLqbVQv0QS8nq0pAUwgbilKOR5piBAIC8LpS' where not exists (select 1 from " + STH_TABLE + ");")
    void ensureTablesExists();

    @SqlUpdate("update " + STH_TABLE + " set tree_size=:tree_size, timestamp=:timestamp, tree_head_signature=:tree_head_signature, sha256_root_hash=:sha256_root_hash")
    void updateSignedTree(@BindBean SignedTreeHead signedTreeHead);
}

